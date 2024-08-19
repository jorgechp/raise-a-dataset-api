package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.AbstractDataset;
import cat.udl.eps.raise.domain.Compliance;
import cat.udl.eps.raise.domain.Dataset;
import cat.udl.eps.raise.domain.RiskDataset;
import cat.udl.eps.raise.repository.ComplianceRepository;
import cat.udl.eps.raise.repository.DatasetRepository;
import cat.udl.eps.raise.repository.IDatasetRepository;
import cat.udl.eps.raise.repository.RiskDatasetRepository;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class AbstractDatasetEventHandler {


    final DatasetRepository datasetRepository;
    final RiskDatasetRepository riskDatasetRepository;

    public AbstractDatasetEventHandler(
            DatasetRepository datasetRepository,
            RiskDatasetRepository riskDatasetRepository
            ) {
        this.datasetRepository = datasetRepository;
        this.riskDatasetRepository = riskDatasetRepository;
    }

    private boolean checkUniqueNameConstraint(AbstractDataset abstractDataset, IDatasetRepository repository){
        return repository.findByName(abstractDataset.getName()).isEmpty();
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(AbstractDataset abstractDataset){
        boolean isUniqueName = false;
        if(abstractDataset instanceof RiskDataset){
            isUniqueName = this.checkUniqueNameConstraint(abstractDataset, riskDatasetRepository);
        }
        if(abstractDataset instanceof Dataset){
            isUniqueName = this.checkUniqueNameConstraint(abstractDataset, datasetRepository);
        }

        if(!isUniqueName){
            throw new RuntimeException("Another dataset exists with the same name: " + abstractDataset.getName());
        }

    }

    @HandleAfterCreate
    public void handleAuthorAfterCreate(AbstractDataset abstractDataset){
        if(abstractDataset instanceof Dataset){
            Dataset d = (Dataset) abstractDataset;
            if(d.getRescuedBy() != null){
                RiskDataset r = this.riskDatasetRepository.findByName(d.getName()).get();
                r.setRescued(true);
                d.setRescued(true);
                this.riskDatasetRepository.save(r);
                this.datasetRepository.save(d);
            }
        }
    }

}
