package cat.udl.eps.raise.config;


import cat.udl.eps.raise.domain.Compliance;
import cat.udl.eps.raise.listener.RabbitMQResponseListener;
import cat.udl.eps.raise.repository.*;
import org.springframework.amqp.core.Queue;
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

    private final UserRepository userRepository;
    private final RaiseInstanceRepository raiseInstanceRepository;

    private final FAIRPrincipleRepository fairPrincipleRepository;

    private final ComplianceRepository complianceRepository;

    private final ValidationRepository validationRepository;

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
        return container;
    }
}
