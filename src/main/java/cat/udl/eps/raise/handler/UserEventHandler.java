package cat.udl.eps.raise.handler;

import cat.udl.eps.raise.domain.User;
import cat.udl.eps.raise.repository.UserRepository;
import cat.udl.eps.raise.util.ScoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    final Logger logger = LoggerFactory.getLogger(User.class);

    final UserRepository userRepository;

    final ScoreUtils scoreUtils;

    public UserEventHandler(UserRepository userRepository, ScoreUtils scoreUtils) {
        this.userRepository = userRepository;
        this.scoreUtils = scoreUtils;
    }

    @HandleBeforeCreate
    public void handleUserPreCreate(User user) {
        logger.info("Before creating: {}", user.toString());
    }

    @HandleBeforeSave
    public void handleUserPreSave(User user) {
        logger.info("Before updating: {}", user.toString());
    }

    @HandleBeforeDelete
    public void handleUserPreDelete(User user) {
        logger.info("Before deleting: {}", user.toString());
    }

    @HandleBeforeLinkSave
    public void handleUserPreLinkSave(User user, Object o) {
        logger.info("Before linking: {} to {}", user.toString(), o.toString());
    }

    @HandleAfterCreate
    public void handleUserPostCreate(User user) {
        logger.info("After creating: {}", user.toString());
        user.encodePassword();
        userRepository.save(user);
    }

    @HandleBeforeSave
    public void handleUserBeforeSave(User user) {
        logger.info("After updating: {}", user.toString());
    }

    @HandleAfterSave
    public void handleUserPostSave(User user) {
        logger.info("After updating: {}", user.toString());
        if (user.isPasswordReset()) {
            user.encodePassword();
        }
        userRepository.save(user);
    }

    @HandleAfterDelete
    public void handleUserPostDelete(User user) {
        logger.info("After deleting: {}", user.toString());
    }

    @HandleAfterLinkSave
    public void handleUserPostLinkSave(User user, Object o) {
        logger.info("After linking: {} to {}", user.toString(), o.toString());
        user.setScore(this.scoreUtils.getScore(user));
        userRepository.save(user);
    }

    @HandleAfterLinkDelete
    public void handleUserPostLinkDelete(User user, Object o) {
        logger.info("After remove linking: {} to {}", user.toString(), o.toString());
        user.setScore(this.scoreUtils.getScore(user));
        userRepository.save(user);
    }
}
