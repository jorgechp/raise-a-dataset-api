package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.repository.FAIRPrincipleRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleEventHandler {



    final FAIRPrincipleRepository fairRepository;

    public FAIRPrincipleEventHandler(FAIRPrincipleRepository fairRepository) {
        this.fairRepository = fairRepository;
    }}
