package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.repository.DatasetRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class DatasetEventHandler {



    final DatasetRepository datasetRepository;

    public DatasetEventHandler(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }}
