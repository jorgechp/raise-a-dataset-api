package cat.udl.eps.raise.service;

import cat.udl.eps.raise.agenda.MissionAgendaEventListener;
import cat.udl.eps.raise.domain.Mission;
import cat.udl.eps.raise.projection.UserStateDTO;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
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

    public boolean checkMission(UserStateDTO userDto, Mission mission) {
        KieSession kieSession = kieContainer.newKieSession();
        MissionAgendaEventListener agendaEventListener = new MissionAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);
        kieSession.insert(mission);
        kieSession.insert(userDto);
        kieSession.fireAllRules( new RuleNameEqualsAgendaFilter(mission.getRuleName()));
        kieSession.dispose();
        return agendaEventListener.isRuleAccepted();
    }
}
