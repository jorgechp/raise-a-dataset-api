package cat.udl.eps.raise.config;

import cat.udl.eps.raise.config.fair_principles.FairPrinciplesList;
import cat.udl.eps.raise.domain.*;
import cat.udl.eps.raise.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
    private final FAIRPrincipleRepository fairPrincipleRepository;

    private final VerificationRepository fairVerificationInstanceRepository;
    private final RaiseInstanceRepository raiseInstanceRepository;


    private User demoUser;

    public DBInitialization(UserRepository userRepository,
                            RepositoryRepository repositoryRepository,
                            DatasetRepository datasetRepository,
                            FAIRPrincipleRepository fairRepository,
                            RaiseInstanceRepository raiseInstanceRepository,
                            VerificationRepository fairVerificationInstanceRepository) {
        this.userRepository = userRepository;
        this.repositoryRepository = repositoryRepository;
        this.datasetRepository = datasetRepository;
        this.fairPrincipleRepository = fairRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.fairVerificationInstanceRepository = fairVerificationInstanceRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        Dataset createdDataset, createdDataset2;
        Repository createdRepository1, createdRepository2;
        RaiseInstance raiseInstance = null, raiseInstance2 = null;
        FAIRPrinciple fairPrinciple = null, fairPrinciple2 = null;

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
            createdRepository1 = repository;

            Repository repository2 = new Repository();
            repository2.setName("DemoRepository2");
            repository2.setAddress("https://demo.repository.com");
            repository2.setDescription("This is a demo repository 2.");
            repository2.setAddedBy(this.demoUser);
            repositoryRepository.save(repository2);
            createdRepository2 = repository2;
        }else{
            createdRepository1 = repositoryRepository.findByName("demoRepository").get();
        }

        if(repositoryRepository.findByName("demoRepository2").isEmpty()){
            Repository repository = new Repository();
            repository.setName("DemoRepository2");
            repository.setAddress("https://demo2.repository.com");
            repository.setDescription("This is another demo repository.");
            repository.setAddedBy(this.demoUser);
            repositoryRepository.save(repository);
            createdRepository2 = repository;
        }else{
            createdRepository2 = repositoryRepository.findByName("demoRepository").get();
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

            Dataset dataset2 = new Dataset();
            dataset2.setName("DemoDataset2");
            dataset2.setCreatedBy("María Dolores Fuertes de Cabeza");
            dataset2.setRegisteredBy("Aurora Macarena Morales");
            dataset2.setDescription("This is a demo dataset.");
            dataset2.setRegistrationDate(LocalDate.now());
            dataset2.setCreationDate(LocalDate.now().minusDays(50));
            authors.add(this.demoUser);
            dataset2.setMaintainedBy(authors);
            datasetRepository.save(dataset2);
            createdDataset2 = dataset2;
        }else{
            createdDataset = datasetRepository.findByName("demoDataset").get();
        }

        if(raiseInstanceRepository.findByDoi("10.1080/02626667.2018.1560449").isEmpty()){
            raiseInstance = new RaiseInstance();
            raiseInstance.setUser(this.demoUser);
            raiseInstance.setDate(LocalDate.now());
            raiseInstance.setDataset(createdDataset);
            raiseInstance.setRepository(createdRepository1);
            raiseInstance.setDoi("10.1080/02626667.2018.1560449");
            raiseInstanceRepository.save(raiseInstance);

        }

        if(raiseInstanceRepository.findByDoi("10.1080/02626667.2018.1560448").isEmpty()){
            raiseInstance2 = new RaiseInstance();
            raiseInstance2.setUser(this.demoUser);
            raiseInstance2.setDate(LocalDate.now());
            raiseInstance2.setDataset(createdDataset);
            raiseInstance2.setRepository(createdRepository2);
            raiseInstance2.setDoi("10.1080/02626667.2018.1560448");
            raiseInstanceRepository.save(raiseInstance2);
        }

        if (fairPrincipleRepository.count() == 0){
            FairPrinciplesList fplistClass = new FairPrinciplesList();
            List<FAIRPrinciple> fpList = fplistClass.getFairPrincipleList();
            for(FAIRPrinciple fp: fpList){
                fairPrincipleRepository.save(fp);
            };
            fairPrinciple = fpList.get(0);
        }

        if(raiseInstance != null && fairVerificationInstanceRepository.findByInstanceId(raiseInstance.getId()).isEmpty()){
            Verification instance = new Verification();
            instance.setInstance(raiseInstance);
            instance.setAuthor(this.demoUser);
            instance.setPrinciple(fairPrinciple);
            instance.setVerificationDate(LocalDate.now());


            fairVerificationInstanceRepository.save(instance);

        }
    }
}
