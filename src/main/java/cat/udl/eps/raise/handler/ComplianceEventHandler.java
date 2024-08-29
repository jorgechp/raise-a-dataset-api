package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.Compliance;
import cat.udl.eps.raise.domain.RaiseInstance;
import cat.udl.eps.raise.repository.ComplianceRepository;
import cat.udl.eps.raise.repository.RaiseInstanceRepository;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class ComplianceEventHandler {


    final ComplianceRepository complianceRepository;
    final RaiseInstanceRepository raiseInstanceRepository;

    public ComplianceEventHandler(
            ComplianceRepository complianceRepository, RaiseInstanceRepository raiseInstanceRepository) {
        this.complianceRepository = complianceRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
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

    @HandleAfterCreate
    public void handleAuthorAfterCreate(Compliance compliance){
        RaiseInstance ri = compliance.getInstance();
        short daysToAdd = ri.getFeedFrequencyInDays();
        LocalDate ld = LocalDate.now().plusDays(daysToAdd);
        ri.setNextFeedAction(ld);
        raiseInstanceRepository.save(ri);
    }

}
