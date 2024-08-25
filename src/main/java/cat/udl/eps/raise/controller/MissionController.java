package cat.udl.eps.raise.controller;

import cat.udl.eps.raise.domain.Mission;
import cat.udl.eps.raise.projection.UserStateDTO;
import cat.udl.eps.raise.repository.MissionRepository;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.service.MissionService;
import cat.udl.eps.raise.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@BasePathAwareController
@RestController
public class MissionController {


  private final UserRepository userRepository;

  private final MissionRepository missionRepository;
  private final MissionService missionService;
  private final StatsService statsService;

  @Autowired
  public MissionController(UserRepository userRepository,
                           MissionService missionService,
                           StatsService statsService,
                           MissionRepository missionRepository) {
    this.userRepository = userRepository;
    this.missionService = missionService;
    this.statsService = statsService;
    this.missionRepository = missionRepository;
  }

  @PostMapping("/missions/check")
  public @ResponseBody ResponseEntity<Boolean> checkMission(@RequestBody Map<String, String> paramData) {
    String username = paramData.get("username");
    Long missionId = Long.valueOf(paramData.get("missionId"));
    Optional<Mission> missionRetrieved = missionRepository.findMissionById(missionId);

    if(userRepository.findByUsername(username).isPresent()
            && missionRetrieved.isPresent()){
      Optional<UserStateDTO> userDto = this.statsService.getUserStats(username);
      Mission mission = missionRetrieved.get();
      if (userDto.isPresent()){
        boolean result = missionService.checkMission(userDto.get(), mission);
        userRepository.save(userDto.get().getUser());
        return ResponseEntity.ok(result);
      }
    }
    return ResponseEntity.notFound().build();
  }
}
