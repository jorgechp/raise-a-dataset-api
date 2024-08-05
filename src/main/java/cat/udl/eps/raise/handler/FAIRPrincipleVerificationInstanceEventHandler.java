package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.FAIRPrincipleVerificationInstance;
import cat.udl.eps.raise.repository.FAIRPrincipleVerificationInstanceRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleVerificationInstanceEventHandler {


    final FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstanceRepository;

    public FAIRPrincipleVerificationInstanceEventHandler(
            FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstanceRepository) {
        this.fairPrincipleVerificationInstanceRepository = fairPrincipleVerificationInstanceRepository;
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(FAIRPrincipleVerificationInstance fairPrincipleVerificationInstance){
        boolean response = this.fairPrincipleVerificationInstanceRepository.existsByInstanceIdAndFairPrincipleId(
                fairPrincipleVerificationInstance.getInstance().getId()
                , fairPrincipleVerificationInstance.getFairPrinciple().getId()
        );
        if(response){
            throw new RuntimeException("Another similar instance exists");
        }
    }

}
