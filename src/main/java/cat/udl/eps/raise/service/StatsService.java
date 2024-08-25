package cat.udl.eps.raise.service;

import cat.udl.eps.raise.domain.User;
import cat.udl.eps.raise.projection.UserStateDTO;
import cat.udl.eps.raise.repository.ComplianceRepository;
import cat.udl.eps.raise.repository.RaiseInstanceRepository;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsService {

    UserRepository userRepository;
    RaiseInstanceRepository raiseInstanceRepository;
    ValidationRepository validationRepository;
    ComplianceRepository complianceRepository;

    @Autowired
    public StatsService(UserRepository userRepository,
                        RaiseInstanceRepository raiseInstanceRepository,
                        ValidationRepository validationRepository,
                        ComplianceRepository complianceRepository) {
        this.userRepository = userRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.validationRepository = validationRepository;
        this.complianceRepository = complianceRepository;
    }

    public Optional<UserStateDTO> getUserStats(String username) {
        UserStateDTO userDto = null;
        if (this.userRepository.findByUsername(username).isPresent()){
            userDto = new UserStateDTO();
            User user = this.userRepository.findByUsername(username).get();
            userDto.setUser(user);
            userDto.setNumberOfDatasetInstances(this.raiseInstanceRepository.countAllByUserUsername(username));
            userDto.setNumberOfIndicators(this.complianceRepository.countAllByAuthorUsername(username));
            userDto.setNumberOfValidations(this.validationRepository.countAllByValidatorUsername(username));
        }
        return Optional.ofNullable(userDto);
    }
}
