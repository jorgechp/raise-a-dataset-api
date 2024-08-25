package cat.udl.eps.raise.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FilenameFilter;

@Configuration

public class DroolsConfig {
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer getKieContainer() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        File missionsDirectory = new File("missions");
        if (missionsDirectory.isDirectory()) {
            File[] drlFiles = missionsDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".drl");
                }
            });

            assert drlFiles != null;
            for (File drlFile : drlFiles) {
                kieFileSystem.write(ResourceFactory.newClassPathResource("missions/" + drlFile.getName()));
            }
        }
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
