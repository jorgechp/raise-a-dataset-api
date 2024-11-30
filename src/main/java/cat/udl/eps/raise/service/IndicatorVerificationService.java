package cat.udl.eps.raise.service;

import cat.udl.eps.raise.config.RabbitMQConfig;
import cat.udl.eps.raise.projection.VerificationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorVerificationService {

    final Logger logger = LoggerFactory.getLogger(IndicatorVerificationService.class);
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;

    @Autowired
    public IndicatorVerificationService(RabbitTemplate rabbitTemplate, RabbitMQConfig rabbitMQConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfig = rabbitMQConfig;
    }

    public void sendVerificationRequest(VerificationRequestDTO request) {
        if (this.rabbitMQConfig.isConnectionStablished()){
            try{
                rabbitTemplate.convertAndSend("RaiseDatasetVerificationQueue", request);
            } catch (AmqpException e){
                logger.error("Error connecting with Automatic verification Service: {0}",
                        e.getMessage());
            }

        }
    }
}
