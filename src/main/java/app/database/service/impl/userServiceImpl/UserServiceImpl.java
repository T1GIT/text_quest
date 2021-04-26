package app.database.service.impl.userServiceImpl;

import app.database.model.user.User;
import app.database.repo.userRepository.UserRepository;
import app.database.service.userService.UserService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @see app.database.service.userService.UserService
 */
@Service
@Transactional
public class UserServiceImpl
        extends AbstractService<User, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
