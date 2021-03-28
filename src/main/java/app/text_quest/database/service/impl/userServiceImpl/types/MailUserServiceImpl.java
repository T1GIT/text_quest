package app.text_quest.database.service.impl.userServiceImpl.types;

import app.text_quest.database.model.user.types.MailUser;
import app.text_quest.database.repo.userRepository.types.MailUserRepository;
import app.text_quest.database.service.userService.types.MailUserService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;


/**
 @see MailUserService */
@Service
public class MailUserServiceImpl
        extends AbstractService<MailUser, MailUserRepository>
        implements MailUserService {

    public MailUserServiceImpl(MailUserRepository repository) {
        super(repository);
    }

    @Override
    public MailUser getByMail(String mail) {
        return repository.findByMail(mail);
    }
}
