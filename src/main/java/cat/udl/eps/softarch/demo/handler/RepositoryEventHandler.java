package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.repository.RepositoryRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class RepositoryEventHandler {


    //TODO find a better name than RepositoryRepository
    final RepositoryRepository repositoryRepository;

    public RepositoryEventHandler(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }}
