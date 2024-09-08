package cat.udl.eps.raise.handler;

import cat.udl.eps.raise.domain.Mission;
import cat.udl.eps.raise.repository.MissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class MissionEventHandler {

    final Logger logger = LoggerFactory.getLogger(Mission.class);

    final MissionRepository MissionRepository;

    public MissionEventHandler(MissionRepository MissionRepository) {
        this.MissionRepository = MissionRepository;
    }

    @HandleBeforeCreate
    public void handleMissionPreCreate(Mission mission) {
        logger.info("Before creating: {}", mission.toString());
    }

    @HandleBeforeSave
    public void handleMissionPreSave(Mission mission) {
        logger.info("Before updating: {}", mission.toString());
    }

    @HandleBeforeDelete
    public void handleMissionPreDelete(Mission mission) {
        mission.getUsersAccepted().forEach(user -> {
            user.getMissionsAccepted().remove(mission);
        });

        mission.getUsersAccomplished().forEach(user -> {
            user.getMissionsAcomplished().remove(mission);
        });

        mission.getUsersAccepted().clear();
        mission.getUsersAccomplished().clear();
    }



    @HandleAfterCreate
    public void handleMissionPostCreate(Mission mission) {
        logger.info("After creating: {}", mission.toString());
    }

    @HandleBeforeSave
    public void handleMissionBeforeSave(Mission mission) {
        logger.info("After updating: {}", mission.toString());
    }

    @HandleAfterSave
    public void handleMissionPostSave(Mission mission) {
        logger.info("After updating: {}", mission.toString());
    }

    @HandleAfterDelete
    public void handleMissionPostDelete(Mission mission) {
        logger.info("After deleting: {}", mission.toString());
    }

    @HandleAfterLinkSave
    public void handleMissionPostLinkSave(Mission mission, Object o) {
        logger.info("After linking: {} to {}", mission.toString(), o.toString());
    }
}
