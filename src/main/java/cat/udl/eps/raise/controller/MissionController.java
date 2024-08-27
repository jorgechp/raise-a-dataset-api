package cat.udl.eps.raise.controller;

import cat.udl.eps.raise.domain.Mission;
import cat.udl.eps.raise.domain.User;
import cat.udl.eps.raise.projection.UserStateDTO;
import cat.udl.eps.raise.repository.MissionRepository;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.service.MissionService;
import cat.udl.eps.raise.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

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
  public @ResponseBody ResponseEntity<List<String>> checkAllMission(@RequestParam String username) {
    List<String> completedMissions = new LinkedList<>();

    Optional<UserStateDTO> userDto = this.statsService.getUserStats(username);
    if (userDto.isPresent()) {
      for (Mission m : userDto.get().getUser().getMissionsAccepted()) {
        boolean result = missionService.checkMission(userDto.get(), m);
        userRepository.save(userDto.get().getUser());
        if (result) {
          completedMissions.add(m.getRuleName());
        }
      }
      return ok(completedMissions);
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/missions/{missionId}/check")
  public @ResponseBody ResponseEntity<Boolean> checkMission(@PathVariable Long missionId,
                                                            @RequestParam String username) {
    Optional<Mission> missionRetrieved = missionRepository.findMissionById(missionId);

    if (userRepository.findByUsername(username).isPresent()
            && missionRetrieved.isPresent()) {
      Optional<UserStateDTO> userDto = this.statsService.getUserStats(username);
      Mission mission = missionRetrieved.get();
      if (userDto.isPresent()) {
        boolean result = missionService.checkMission(userDto.get(), mission);
        userRepository.save(userDto.get().getUser());
        return ok(result);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/missions/acceptedByUser/{idUser}")
  public ResponseEntity<?> getAcceptedMissionsByUser(
          PersistentEntityResourceAssembler resourceAssembler, @PathVariable Long idUser) {

    Optional<User> optUser = this.userRepository.findById(idUser);
    if(optUser.isPresent()) {
      Set<Mission> missionsToRetrieve = new HashSet<>(optUser.get().getMissionsAccepted());
      return ok(resourceAssembler.toCollectionModel(missionsToRetrieve));
    }
    return notFound().build();
  }

  @GetMapping("/missions/accomplishedByUser/{idUser}")
  public ResponseEntity<?> getAccomplishedMissionsByUser(
          PersistentEntityResourceAssembler resourceAssembler, @PathVariable Long idUser) {

    Optional<User> optUser = this.userRepository.findById(idUser);
    if(optUser.isPresent()) {
      Set<Mission> missionsToRetrieve = new HashSet<>(optUser.get().getMissionsAcomplished());
      return ok(resourceAssembler.toCollectionModel(missionsToRetrieve));
    }
    return notFound().build();
  }

  @GetMapping("/missions/othersForUser/{idUser}")
  public ResponseEntity<?> getOtherMissionsForUser(
          PersistentEntityResourceAssembler resourceAssembler, @PathVariable Long idUser) {

    Optional<User> optUser = this.userRepository.findById(idUser);
    if(optUser.isPresent()){
      Iterable<Mission> missions = this.missionRepository.findAllByOrderByLevelAsc();
      List<Mission> missionsToRetrieve = new LinkedList<>();
      Stream<Mission> stream = StreamSupport.stream(missions.spliterator(), false);
      Set<Mission> missionsToRemove = new HashSet<>(optUser.get().getMissionsAccepted());
      missionsToRemove.addAll(optUser.get().getMissionsAcomplished());

      missions.forEach(mission -> {
        if(!missionsToRemove.contains(mission)){
          missionsToRetrieve.add(mission);
        }
      });

      return ok(resourceAssembler.toCollectionModel(missionsToRetrieve));
    }
    return notFound().build();
  }
}
