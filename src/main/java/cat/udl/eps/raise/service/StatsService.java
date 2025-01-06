package cat.udl.eps.raise.service;

import cat.udl.eps.raise.config.ScoreConfig;
import cat.udl.eps.raise.domain.User;
import cat.udl.eps.raise.projection.UserStateDTO;
import cat.udl.eps.raise.repository.ComplianceRepository;
import cat.udl.eps.raise.repository.RaiseInstanceRepository;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.repository.ValidationRepository;
import cat.udl.eps.raise.util.ScoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsService {

    UserRepository userRepository;
    RaiseInstanceRepository raiseInstanceRepository;
    ValidationRepository validationRepository;
    ComplianceRepository complianceRepository;

    private final ScoreUtils scoreUtils;


    @Autowired
    public StatsService(UserRepository userRepository,
                        RaiseInstanceRepository raiseInstanceRepository,
                        ValidationRepository validationRepository,
                        ComplianceRepository complianceRepository, ScoreUtils scoreUtils) {
        this.userRepository = userRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.validationRepository = validationRepository;
        this.complianceRepository = complianceRepository;
        this.scoreUtils = scoreUtils;
    }


    public Optional<UserStateDTO> getUserStats(String username) {
        UserStateDTO userDto = null;
        if (this.userRepository.findByUsername(username).isPresent()){
            userDto = new UserStateDTO();
            User user = this.userRepository.findByUsername(username).get();
            userDto.setUser(user);
            userDto.setNumberOfValidations(user.getValidations().size());
            userDto.setNumberOfCompliances(user.getCompliances().size());
            userDto.setNumberOfDatasetInstances(user.getDatasetsMaintained().size());
            userDto.setNumberOfDatasetRescued(user.getDatasetsRescued().size());
            userDto.setTotalScore(this.scoreUtils.getScore(user));
        }
        return Optional.ofNullable(userDto);
    }
}
