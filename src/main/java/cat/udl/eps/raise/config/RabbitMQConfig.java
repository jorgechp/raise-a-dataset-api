package cat.udl.eps.raise.config;


import cat.udl.eps.raise.listener.RabbitMQResponseListener;
import cat.udl.eps.raise.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    private final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);


    private final UserRepository userRepository;
    private final RaiseInstanceRepository raiseInstanceRepository;

    private final FAIRPrincipleRepository fairPrincipleRepository;

    private final ComplianceRepository complianceRepository;

    private final ValidationRepository validationRepository;
    private boolean isConnectionStablished;

    public RabbitMQConfig(UserRepository userRepository,
                          RaiseInstanceRepository raiseInstanceRepository,
                          FAIRPrincipleRepository fairPrincipleRepository,
                          ComplianceRepository complianceRepository,
                          ValidationRepository validationRepository
    ) {
        this.userRepository = userRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.fairPrincipleRepository = fairPrincipleRepository;
        this.complianceRepository = complianceRepository;
        this.validationRepository = validationRepository;
        this.isConnectionStablished = false;
    }

    @Bean
    public Queue verificationQueue() {
        return new Queue("RaiseDatasetVerificationQueue", true);
    }

    @Bean
    public Queue responseQueue() {
        return new Queue("RaiseDatasetResponseQueue", true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    public void checkConnection(ConnectionFactory connectionFactory) {
        try (Connection ignored = connectionFactory.createConnection()) {
            this.isConnectionStablished = true;
        } catch (AmqpException e) {
            this.isConnectionStablished = false;
        }
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(responseQueue());
        container.setMessageListener(new RabbitMQResponseListener(
                userRepository,
                raiseInstanceRepository,
                fairPrincipleRepository,
                complianceRepository,
                validationRepository));
        checkConnection(connectionFactory);
        if(this.isConnectionStablished)
            return container;
        else
            return null;
    }

    public boolean isConnectionStablished() {
        return isConnectionStablished;
    }
}
