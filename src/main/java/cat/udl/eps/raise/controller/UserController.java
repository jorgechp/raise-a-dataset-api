package cat.udl.eps.raise.controller;

import cat.udl.eps.raise.domain.User;
import cat.udl.eps.raise.repository.UserRepository;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@BasePathAwareController
public class UserController {


  UserRepository userRepository = null;

  public UserController( UserRepository userRepository) {
    this.userRepository = userRepository;
  }



  @PostMapping("/password")
  public @ResponseBody PersistentEntityResource changeUserPassword(PersistentEntityResourceAssembler resourceAssembler,
                                                                   @RequestBody Map<String, String> passwordData) {
    String newPassword = passwordData.get("password");
    String currentPassword = passwordData.get("currentpassword");

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!User.passwordEncoder.matches(currentPassword, user.getPassword())) {
      throw new IllegalArgumentException("Current password is incorrect");
    }

    user.setPassword(User.passwordEncoder.encode(newPassword));
    this.userRepository.save(user);

    return resourceAssembler.toFullResource(user);
  }
}
