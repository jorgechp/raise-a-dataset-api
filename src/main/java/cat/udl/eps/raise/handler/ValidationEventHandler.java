package cat.udl.eps.raise.handler;


import cat.udl.eps.raise.domain.Validation;
import cat.udl.eps.raise.repository.ValidationRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.stereotype.Component;

@Component
@org.springframework.data.rest.core.annotation.RepositoryEventHandler
public class ValidationEventHandler {


    final ValidationRepository validationRepository;

    public ValidationEventHandler(
            ValidationRepository validationRepository) {
        this.validationRepository = validationRepository;
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Validation validation){
        boolean response = this.validationRepository.existsByComplianceIdAndValidatorId(
                validation.getId()
                ,validation.getValidator().getId()
        );
        if(response){
            throw new RuntimeException("Another similar validation exists");
        }
    }

}
