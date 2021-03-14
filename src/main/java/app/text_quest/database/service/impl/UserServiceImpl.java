package app.text_quest.database.service.impl;

import app.text_quest.database.model.user.User;
import app.text_quest.database.repo.UserRepository;
import app.text_quest.database.service.UserService;
import app.text_quest.database.util.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl
        extends AbstractServiceImpl<User, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}
