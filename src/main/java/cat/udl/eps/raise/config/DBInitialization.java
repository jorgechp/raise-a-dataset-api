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

    private final ComplianceRepository fairVerificationInstanceRepository;
    private final ValidationRepository validationRepository;
    private final RaiseInstanceRepository raiseInstanceRepository;
    private final RiskDatasetRepository riskDatasetRepository;
    private final MissionRepository missionRepository;

    private final RoleRepository roleRepository;


    private User demoUser;



    public DBInitialization(UserRepository userRepository,
                            RepositoryRepository repositoryRepository,
                            DatasetRepository datasetRepository,
                            FAIRPrincipleRepository fairRepository,
                            RaiseInstanceRepository raiseInstanceRepository,
                            ComplianceRepository fairVerificationInstanceRepository,
                            MissionRepository missionRepository,
                            RiskDatasetRepository riskDatasetRepository,
                            RoleRepository roleRepository,
                            ValidationRepository validationRepository) {
        this.userRepository = userRepository;
        this.repositoryRepository = repositoryRepository;
        this.datasetRepository = datasetRepository;
        this.fairPrincipleRepository = fairRepository;
        this.raiseInstanceRepository = raiseInstanceRepository;
        this.fairVerificationInstanceRepository = fairVerificationInstanceRepository;
        this.riskDatasetRepository = riskDatasetRepository;
        this.missionRepository = missionRepository;
        this.roleRepository = roleRepository;
        this.validationRepository = validationRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        Dataset createdDataset, createdDataset2;
        Repository createdRepository1, createdRepository2;
        RaiseInstance raiseInstance = null, raiseInstance2 = null;
        FAIRPrincipleIndicator fairPrincipleIndicator = null, fairPrincipleIndicator2 = null;

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userole = new Role();
        userole.setName("ROLE_USER");
        Role guestole = new Role();
        guestole.setName("ROLE_GUEST");
        Role bannedRole = new Role();
        bannedRole.setName("ROLE_BAN");
        Role botRole = new Role();
        botRole.setName("ROLE_BOT");

        roleRepository.save(adminRole);
        roleRepository.save(userole);
        roleRepository.save(guestole);
        roleRepository.save(bannedRole);
        roleRepository.save(botRole);

        // Automatic validation user

        User userBot = new User();
        userBot.setUsername("ValidatorBot");
        userBot.setEmail("fake@mail.raise.dataset");
        userBot.setPassword("12345IdontCareThisUserCannotLogin");
        userBot.encodePassword();
        userBot.setRoles(Arrays.asList(adminRole, userole));
        userRepository.save(userBot);

        // Default user
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setUsername("admin");
            user.setPassword(defaultPassword);
            user.encodePassword();
            user.setRoles(Arrays.asList(adminRole, userole));
            userRepository.save(user);
            this.demoUser = user;
        }else{
            this.demoUser = userRepository.findByUsername("demo").get();
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setEmail("user@sample.app");
            user.setUsername("user");
            user.setPassword(defaultPassword);
            user.encodePassword();
            user.setRoles(List.of(userole));
            userRepository.save(user);
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
            dataset.setRescued(false);
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
            dataset.setRescued(false);
            datasetRepository.save(dataset2);
            createdDataset2 = dataset2;
        }else{
            createdDataset = datasetRepository.findByName("demoDataset").get();
        }

        if(riskDatasetRepository.findByName("demoRiskDataset").isEmpty()){
            RiskDataset rd = new RiskDataset();
            rd.setName("DataInRisk");
            rd.setCategory(RISKCategories.CLOUD_STORAGE);
            rd.setDescription("This dataset is in risk of extinction");
            rd.setCreatedBy("Arturo Pérez");
            rd.setRegistrationDate(LocalDate.now());
            rd.setCreationDate(LocalDate.now().minusDays(55));
            rd.setRegisteredBy("Aurora Macarena Morales");
            rd.setAddress("https://unstable.limited.storage.cloud/id/34fajsj3f3YhFs");
            rd.setRescued(false);
            riskDatasetRepository.save(rd);
        }

        if(raiseInstanceRepository.findByUniqueIdentifier("10.1080/02626667.2018.1560449").isEmpty()){
            raiseInstance = new RaiseInstance();
            raiseInstance.setUser(this.demoUser);
            raiseInstance.setDate(LocalDate.now());
            raiseInstance.setDataset(createdDataset);
            raiseInstance.setRepository(createdRepository1);
            raiseInstance.setUniqueIdentifier("10.1080/02626667.2018.1560449");
            raiseInstance.setAgreeToRaise(false);
            raiseInstanceRepository.save(raiseInstance);

        }

        if(raiseInstanceRepository.findByUniqueIdentifier("10.1080/02626667.2018.1560448").isEmpty()){
            raiseInstance2 = new RaiseInstance();
            raiseInstance2.setUser(this.demoUser);
            raiseInstance2.setDate(LocalDate.now());
            raiseInstance2.setDataset(createdDataset);
            raiseInstance2.setRepository(createdRepository2);
            raiseInstance2.setAgreeToRaise(true);
            raiseInstance2.setNextFeedAction(LocalDate.of(2023, 9, 3));
            raiseInstance2.setFeedFrequencyInDays((short) 10);
            raiseInstance2.setUniqueIdentifier("10.1080/02626667.2018.1560448");
            raiseInstanceRepository.save(raiseInstance2);
        }

        if(raiseInstanceRepository.findByUniqueIdentifier("10.1080/12626667.2018.1560448").isEmpty()){
            RaiseInstance raiseInstance3 = new RaiseInstance();
            raiseInstance3.setUser(this.demoUser);
            raiseInstance3.setDate(LocalDate.now());
            raiseInstance3.setDataset(createdDataset);
            raiseInstance3.setRepository(createdRepository2);
            raiseInstance3.setAgreeToRaise(true);
            raiseInstance3.setNextFeedAction(LocalDate.of(2025, 9, 3));
            raiseInstance3.setFeedFrequencyInDays((short) 10);
            raiseInstance3.setUniqueIdentifier("10.1080/12626667.2018.1560448");
            raiseInstanceRepository.save(raiseInstance3);
        }

        if (fairPrincipleRepository.count() == 0){
            FairPrinciplesList fplistClass = new FairPrinciplesList();
            List<FAIRPrincipleIndicator> fpList = fplistClass.getFairPrincipleList();
            for(FAIRPrincipleIndicator fp: fpList){
                fairPrincipleRepository.save(fp);
            };
            fairPrincipleIndicator = fpList.get(0);
        }

        if(raiseInstance != null && fairVerificationInstanceRepository.findByInstanceId(raiseInstance.getId()).isEmpty()){
            Compliance instance = new Compliance();
            instance.setInstance(raiseInstance);
            instance.setAuthor(this.demoUser);
            instance.setPrinciple(fairPrincipleIndicator);
            instance.setVerificationDate(LocalDate.now());


            fairVerificationInstanceRepository.save(instance);

            Validation validationInstance = new Validation();
            validationInstance.setValidationDate(LocalDate.now());
            validationInstance.setPositive(true);
            validationInstance.setValidator(demoUser);
            validationInstance.setCompliance(instance);
            validationRepository.save(validationInstance);

        }

        if(missionRepository.findMissionByRuleName("CreateADatasetInstanceMission").isEmpty()){
            Mission m = new Mission();
            m.setRuleName("CreateADatasetInstanceMission");
            m.setDescription("Create your first dataset instance");
            m.setPoints(10);
            m.setLevel(0);
            m.setName("Create a dataset Instance");
            missionRepository.save(m);

            demoUser.getMissionsAccepted().add(m);
            userRepository.save(demoUser);
        }

        if(missionRepository.findMissionByRuleName("ValidateAnIndicator").isEmpty()){
            Mission m = new Mission();
            m.setRuleName("ValidateAnInstanceMission");
            m.setDescription("Validate one indicator");
            m.setPoints(10);
            m.setLevel(0);
            m.setName("Validate an indicator");
            missionRepository.save(m);
        }

    }
}
