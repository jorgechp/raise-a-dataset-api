package cat.udl.eps.raise.service;

import cat.udl.eps.raise.projection.UserStateDTO;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    private final KieContainer kieContainer;
    @Autowired
    public MissionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public boolean getProductDiscount(UserStateDTO userDto) {
        //get the stateful session
        KieSession kieSession = kieContainer.newKieSession("missionsSession");
        kieSession.insert(userDto);
        kieSession.fireAllRules();
        kieSession.dispose();
        return true;
    }
}
