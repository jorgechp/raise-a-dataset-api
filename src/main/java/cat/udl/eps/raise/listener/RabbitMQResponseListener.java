package cat.udl.eps.raise.listener;

import cat.udl.eps.raise.domain.*;
import cat.udl.eps.raise.projection.VerificationIndicatorResponseDTO;
import cat.udl.eps.raise.projection.VerificationResponseDTO;
import cat.udl.eps.raise.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Listener for RabbitMQ messages from the "RaiseDatasetResponseQueue" queue.
 */
@RabbitListener(queues = RabbitMQResponseListener.RAISE_DATASET_RESPONSE_QUEUE)
public class RabbitMQResponseListener implements MessageListener {

    public static final String RAISE_DATASET_RESPONSE_QUEUE = "RaiseDatasetResponseQueue";
    private static final String VALIDATOR_BOT_USERNAME = "ValidatorBot";
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQResponseListener.class);

    // Repositories for accessing data from the database
    private final UserRepository userRepository;
    private final RaiseInstanceRepository raiseInstanceRepository;
    private final FAIRPrincipleRepository fairPrincipleRepository;
    private final ComplianceRepository complianceRepository;
    private final ValidationRepository validationRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param userRepository          Repository for user data.
     * @param raiseInstanceRepository Repository for raise instance data.
     * @param fairPrincipleRepository Repository for FAIR principles data.
     * @param complianceRepository    Repository for compliance data.
     * @param validationRepository    Repository for validation data.
     */
    public RabbitMQResponseListener(UserRepository userRepository,
                                    RaiseInstanceRepository raiseInstanceRepository,
                                    FAIRPrincipleRepository fairPrincipleRepository,
                                    ComplianceRepository complianceRepository,
                                    ValidationRepository validationRepository) {
        this.userRepository = userRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.fairPrincipleRepository = fairPrincipleRepository;
        this.complianceRepository = complianceRepository;
        this.validationRepository = validationRepository;
    }

    /**
     * Checks if any validation indicator result is negative and collects comments.
     *
     * @param verificationIndicatorResultList List of verification indicator results.
     * @return Optional containing comments if any validation is negative.
     */
    private Optional<String> isValidationErrorNegative(
            List<VerificationIndicatorResponseDTO> verificationIndicatorResultList) {
        StringBuilder comment = new StringBuilder();
        for (VerificationIndicatorResponseDTO verificationIndicatorResult : verificationIndicatorResultList) {
            if (!verificationIndicatorResult.isValid()) {
                if (!comment.isEmpty()) {
                    comment.append("\n");
                }
                comment.append(verificationIndicatorResult.getCommentValue());
            }
        }
        return !comment.isEmpty() ? Optional.of(comment.toString()) : Optional.empty();
    }

    /**
     * Handles incoming RabbitMQ messages.
     *
     * @param message The incoming message from the queue.
     */
    @Override
    @RabbitHandler
    public void onMessage(Message message) {
        String jsonAsString = new String(message.getBody());
        try {
            VerificationResponseDTO response = new ObjectMapper().readValue(jsonAsString, new TypeReference<>() {
            });
            processResponse(response);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON message", e);
            throw new RuntimeException("Error processing JSON message", e);
        }
    }

    private void processResponse(VerificationResponseDTO response) {
        userRepository.findByUsername(VALIDATOR_BOT_USERNAME).ifPresent(botUser ->
                raiseInstanceRepository.findById(response.getInstanceId()).ifPresent(raiseInstance -> {
                    Map<String, List<VerificationIndicatorResponseDTO>> results = response.getResult();
                    results.forEach((indicatorName, verificationIndicatorResultList) ->
                            processIndicatorResults(
                                    botUser,
                                    raiseInstance,
                                    indicatorName,
                                    verificationIndicatorResultList));
                })
        );
    }

    private void processIndicatorResults(User botUser,
                                         RaiseInstance raiseInstance,
                                         String indicatorName,
                                         List<VerificationIndicatorResponseDTO> verificationIndicatorResultList) {
        Optional<String> validationComments = isValidationErrorNegative(verificationIndicatorResultList);
        fairPrincipleRepository.findByNamePrefix(indicatorName).ifPresent(indicator -> {
            LocalDate currentDate = LocalDate.now();
            Compliance compliance = createCompliance(botUser, raiseInstance, indicator, currentDate);
            validationRepository.save(createValidation(botUser, compliance, currentDate, validationComments));
        });
    }

    private Compliance createCompliance(User botUser,
                                        RaiseInstance raiseInstance,
                                        FAIRPrincipleIndicator indicator, LocalDate currentDate) {
        Compliance compliance = new Compliance();
        compliance.setAuthor(botUser);
        compliance.setInstance(raiseInstance);
        compliance.setPrinciple(indicator);
        compliance.setVerificationDate(currentDate);
        return complianceRepository.save(compliance);
    }

    private Validation createValidation(User botUser,
                                        Compliance compliance,
                                        LocalDate currentDate, Optional<String> validationComments) {
        Validation validation = new Validation();
        validation.setValidator(botUser);
        validation.setCompliance(compliance);
        validation.setValidationDate(currentDate);
        validationComments.ifPresent(comments -> {
            boolean isValid = comments.isEmpty();
            validation.setPositive(isValid);
            if (!isValid) {
                validation.setNegativeComment(comments);
            }
        });
        return validation;
    }
}
