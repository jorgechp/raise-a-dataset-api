package cat.udl.eps.softarch.demo.handler;


import cat.udl.eps.softarch.demo.repository.DatasetRepository;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class DatasetEventHandler {



    final DatasetRepository datasetRepository;

    public DatasetEventHandler(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }}
