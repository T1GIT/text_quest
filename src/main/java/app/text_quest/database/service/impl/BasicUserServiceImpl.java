package app.text_quest.database.service.impl;

import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.repo.BasicUserRepository;
import app.text_quest.database.service.BasicUserService;
import app.text_quest.database.util.AbstractServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BasicUserServiceImpl
        extends AbstractServiceImpl<BasicUser, BasicUserRepository>
        implements BasicUserService {

    public BasicUserServiceImpl(BasicUserRepository repository) {
        super(repository);
    }

    @Override
    public BasicUser getByMail(String mail) {
        return repository.findByMail(mail);
    }
}
