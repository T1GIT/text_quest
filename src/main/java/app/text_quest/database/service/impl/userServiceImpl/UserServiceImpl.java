package app.text_quest.database.service.impl.userServiceImpl;

import app.text_quest.database.model.user.User;
import app.text_quest.database.repo.userRepository.UserRepository;
import app.text_quest.database.service.userService.UserService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 @see app.text_quest.database.service.userService.UserService */
@Service
@Transactional
public class UserServiceImpl
        extends AbstractService<User, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
