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
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration

public class DroolsConfig {
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer getKieContainer()  {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        ClassLoader classLoader = getClass().getClassLoader();
        URL missionsUrl = classLoader.getResource("missions");

        if (missionsUrl != null) {
            String decodedPath = URLDecoder.decode(missionsUrl.getFile(), StandardCharsets.UTF_8);
            File missionsDirectory = new File(decodedPath);

            if (missionsDirectory.isDirectory()) {
                File[] drlFiles = missionsDirectory.listFiles((dir, name) -> name.endsWith(".drl"));

                assert drlFiles != null;
                for (File drlFile : drlFiles) {
                    kieFileSystem.write(ResourceFactory.newClassPathResource("missions/" + drlFile.getName()));
                }
            } else {
                System.out.println("missions directory not found or not a directory");
            }
        } else {
            System.out.println("missions resource not found");
        }

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
