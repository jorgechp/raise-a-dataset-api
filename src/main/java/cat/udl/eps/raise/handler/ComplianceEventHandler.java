package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.Compliance;
import cat.udl.eps.raise.repository.ComplianceRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class ComplianceEventHandler {


    final ComplianceRepository complianceRepository;

    public ComplianceEventHandler(
            ComplianceRepository complianceRepository) {
        this.complianceRepository = complianceRepository;
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Compliance compliance){
        boolean response = this.complianceRepository.existsByInstanceIdAndPrincipleId(
                compliance.getInstance().getId()
                , compliance.getPrinciple().getId()
        );
        if(response){
            throw new RuntimeException("Another similar instance exists");
        }
    }

}
