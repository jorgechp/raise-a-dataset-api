package cat.udl.eps.raise.util;

import cat.udl.eps.raise.config.ScoreConfig;
import cat.udl.eps.raise.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreUtils {
    private final ScoreConfig scoreConfig;

    @Autowired
    public ScoreUtils(ScoreConfig scoreConfig) {
        this.scoreConfig = scoreConfig;
    }

    public int getScore(User user) {
        int score = 0;
        score += user.getValidations().size() * scoreConfig.getValidationWeight();
        score += user.getCompliances().size() * scoreConfig.getComplianceWeight();
        score += user.getDatasetsMaintained().size() * scoreConfig.getDatasetMaintainedWeight();
        score += user.getDatasetsRescued().size() * scoreConfig.getDatasetRescuedWeight();
        return score;
    }
}
