package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.Verification;
import cat.udl.eps.raise.repository.VerificationRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleVerificationInstanceEventHandler {


    final VerificationRepository verificationRepository;

    public FAIRPrincipleVerificationInstanceEventHandler(
            VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Verification verification){
        boolean response = this.verificationRepository.existsByInstanceIdAndPrincipleId(
                verification.getInstance().getId()
                , verification.getPrinciple().getId()
        );
        if(response){
            throw new RuntimeException("Another similar instance exists");
        }
    }

}
