package app.database.service.impl.userServiceImpl.types;

import app.database.model.user.types.MailUser;
import app.database.repo.userRepository.types.MailUserRepository;
import app.database.service.userService.types.MailUserService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;


/**
 * @see MailUserService
 */
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
