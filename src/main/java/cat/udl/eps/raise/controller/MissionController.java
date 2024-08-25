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
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
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

  @GetMapping("/missions/check")
  public @ResponseBody ResponseEntity<List<String>> checkAllMission( @RequestParam String username) {
    List<String> completedMissions = new LinkedList<>();

      Optional<UserStateDTO> userDto = this.statsService.getUserStats(username);
      if(userDto.isPresent()){
        for(Mission m: userDto.get().getUser().getMissionsAccepted()){
          boolean result = missionService.checkMission(userDto.get(), m);
          userRepository.save(userDto.get().getUser());
          if(result){
            completedMissions.add(m.getRuleName());
          }
        }
        return ResponseEntity.ok(completedMissions);
      }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/missions/{missionId}/check")
  public @ResponseBody ResponseEntity<Boolean> checkMission(@PathVariable Long missionId,
                                                            @RequestParam String username) {
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
