package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.repository.FAIRPrincipleVerificationInstanceRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleVerificationInstanceEventHandler {


    final FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstance;

    public FAIRPrincipleVerificationInstanceEventHandler(FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstance) {
        this.fairPrincipleVerificationInstance = fairPrincipleVerificationInstance;
    }}
