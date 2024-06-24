package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.repository.FAIRPrincipleRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class FAIRPrincipleEventHandler {



    final FAIRPrincipleRepository fairRepository;

    public FAIRPrincipleEventHandler(FAIRPrincipleRepository fairRepository) {
        this.fairRepository = fairRepository;
    }}
