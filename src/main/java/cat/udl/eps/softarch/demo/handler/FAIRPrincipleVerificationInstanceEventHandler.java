package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.repository.FAIRPrincipleVerificationInstanceRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleVerificationInstanceEventHandler {


    final FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstance;

    public FAIRPrincipleVerificationInstanceEventHandler(FAIRPrincipleVerificationInstanceRepository fairPrincipleVerificationInstance) {
        this.fairPrincipleVerificationInstance = fairPrincipleVerificationInstance;
    }}
