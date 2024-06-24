package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.DatasetRepository;
import cat.udl.eps.softarch.demo.repository.FAIRPrincipleRepository;
import cat.udl.eps.softarch.demo.repository.RepositoryRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;

    private final RepositoryRepository repositoryRepository;
    private final DatasetRepository datasetRepository;
    private final FAIRPrincipleRepository fairRepository;


    private User demoUser;

    public DBInitialization(UserRepository userRepository,
                            RepositoryRepository repositoryRepository,
                            DatasetRepository datasetRepository,
                            FAIRPrincipleRepository fairRepository) {
        this.userRepository = userRepository;
        this.repositoryRepository = repositoryRepository;
        this.datasetRepository = datasetRepository;
        this.fairRepository = fairRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (userRepository.findByUsername("demo").isEmpty()) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setUsername("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
            this.demoUser = user;
        }else{
            this.demoUser = userRepository.findByUsername("demo").get();
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsByUsername("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setUsername("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }
        }

        if(repositoryRepository.findByName("demoRepository").isEmpty()){
            Repository repository = new Repository();
            repository.setName("DemoRepository");
            repository.setUrl("https://demo.repository.com");
            repository.setDescription("This is a demo repository.");
            repositoryRepository.save(repository);
        }

        if(datasetRepository.findByName("demoDataset").isEmpty()){
            Dataset dataset = new Dataset();
            dataset.setName("DemoDataset");
            dataset.setAuthor("María Dolores Fuertes de Cabeza");
            dataset.setDescription("This is a demo dataset.");
            dataset.setRegistrationDate(LocalDate.now());
            dataset.setCreationDate(LocalDate.now().minusDays(50));
            Set<User> authors = new HashSet<>();
            authors.add(this.demoUser);
            dataset.setAuthorInSystem(authors);
            datasetRepository.save(dataset);
        }

        if(fairRepository.findByName("demoFAIRPRINCIPLE").isEmpty()){
            FAIRPrinciple principle = new FAIRPrinciple();
            principle.setName("demoFAIRPRINCIPLE");
            principle.setDescription("This is a demo dataset.");
            principle.setCategory(FAIRCategories.ACCESIBILITY);
            principle.setNamePrefix("A1.1");
            principle.setUrl("https://demo.principle.com");

            fairRepository.save(principle);
        }

    }
}
