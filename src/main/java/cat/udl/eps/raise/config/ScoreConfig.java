package cat.udl.eps.raise.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ScoreConfig {

    @Value("${score.validation.weight}")
    private int validationWeight;

    @Value("${score.compliance.weight}")
    private int complianceWeight;

    @Value("${score.dataset.maintained.weight}")
    private int datasetMaintainedWeight;

    @Value("${score.dataset.rescued.weight}")
    private int datasetRescuedWeight;

}