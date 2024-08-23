package cat.udl.eps.raise.controller;

import cat.udl.eps.raise.projection.UserStateDTO;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@BasePathAwareController
@RestController
public class MissionController {


  private final UserRepository userRepository;
  private final MissionService missionService;

  @Autowired
  public MissionController(UserRepository userRepository, MissionService missionService) {
    this.userRepository = userRepository;
    this.missionService = missionService;
  }

  @PostMapping("/missions/check")
  public @ResponseBody PersistentEntityResource checkMissions(PersistentEntityResourceAssembler resourceAssembler,
                                                                   @RequestBody Map<String, String> passwordData) {
    String userId = passwordData.get("userId");
    String missionId = passwordData.get("missionId");

    UserStateDTO userDto = new UserStateDTO();
    userDto.setNumberOfVerifications(3);

    missionService.getProductDiscount(userDto);


    return resourceAssembler.toFullResource(userDto);
  }
}
