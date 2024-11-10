package cat.udl.eps.raise.service;

import cat.udl.eps.raise.projection.VerificationRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndicatorVerificationService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendVerificationRequest(VerificationRequestDTO request) {
        rabbitTemplate.convertAndSend("verificationQueue", request);
    }
}
