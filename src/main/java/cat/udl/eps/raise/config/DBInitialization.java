package cat.udl.eps.raise.config;

import cat.udl.eps.raise.domain.*;
import cat.udl.eps.raise.repository.*;
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
    private final RaiseInstanceRepository raiseInstanceRepository;


    private User demoUser;

    public DBInitialization(UserRepository userRepository,
                            RepositoryRepository repositoryRepository,
                            DatasetRepository datasetRepository,
                            FAIRPrincipleRepository fairRepository,
                            RaiseInstanceRepository raiseInstanceRepository) {
        this.userRepository = userRepository;
        this.repositoryRepository = repositoryRepository;
        this.datasetRepository = datasetRepository;
        this.fairRepository = fairRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        Dataset createdDataset;
        Repository createdRepository;

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
            repository.setAddress("https://demo.repository.com");
            repository.setDescription("This is a demo repository.");
            repository.setAddedBy(this.demoUser);
            repositoryRepository.save(repository);
            createdRepository = repository;
        }else{
            createdRepository = repositoryRepository.findByName("demoRepository").get();
        }


        if(datasetRepository.findByName("demoDataset").isEmpty()){
            Dataset dataset = new Dataset();
            dataset.setName("DemoDataset");
            dataset.setCreatedBy("María Dolores Fuertes de Cabeza");
            dataset.setRegisteredBy("Aurora Macarena Morales");
            dataset.setDescription("This is a demo dataset.");
            dataset.setRegistrationDate(LocalDate.now());
            dataset.setCreationDate(LocalDate.now().minusDays(50));
            Set<User> authors = new HashSet<>();
            authors.add(this.demoUser);
            dataset.setMaintainedBy(authors);
            datasetRepository.save(dataset);
            createdDataset = dataset;
        }else{
            createdDataset = datasetRepository.findByName("demoDataset").get();
        }

        if(raiseInstanceRepository.findByDoi("10.1080/02626667.2018.1560449").isEmpty()){
            RaiseInstance raiseInstance = new RaiseInstance();
            raiseInstance.setUser(this.demoUser);
            raiseInstance.setDate(LocalDate.now());
            raiseInstance.setDataset(createdDataset);
            raiseInstance.setRepository(createdRepository);
            raiseInstance.setDoi("10.1080/02626667.2018.1560449");
            raiseInstanceRepository.save(raiseInstance);
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