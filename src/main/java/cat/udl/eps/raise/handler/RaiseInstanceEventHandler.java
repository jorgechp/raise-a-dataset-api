package cat.udl.eps.raise.handler;

import cat.udl.eps.raise.projection.VerificationRequestDTO;
import cat.udl.eps.raise.domain.RaiseInstance;
import cat.udl.eps.raise.repository.RaiseInstanceRepository;
import cat.udl.eps.raise.service.IndicatorVerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class RaiseInstanceEventHandler {
    final Logger logger = LoggerFactory.getLogger(RaiseInstance.class);
    final RabbitTemplate rabbitTemplate;
    final IndicatorVerificationService indicatorVerificationService;

    final RaiseInstanceRepository raiseInstanceRepository;

    public RaiseInstanceEventHandler(RabbitTemplate rabbitTemplate,
                                     RaiseInstanceRepository raiseInstanceRepository,
                                     IndicatorVerificationService indicatorVerificationService) {
        this.rabbitTemplate = rabbitTemplate;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.indicatorVerificationService = indicatorVerificationService;
    }

    private void sendVerificationRequest(RaiseInstance instance) {
        VerificationRequestDTO requestDTO = new VerificationRequestDTO(instance.getId(), instance.getUniqueIdentifier());
        this.indicatorVerificationService.sendVerificationRequest(requestDTO);
    }

    @HandleAfterSave
    public void handleMissionAfterSave(RaiseInstance instance) {
        sendVerificationRequest(instance);
    }

    @HandleAfterCreate
    public void handleMissionAfterCreate(RaiseInstance instance) {
        sendVerificationRequest(instance);
    }
}
