package app.text_quest.database.service.impl.userServiceImpl.types;

import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.repo.userRepository.types.OauthUserRepository;
import app.text_quest.database.service.userService.types.OauthUserService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;


/**
 * @see app.text_quest.database.service.userService.types.OauthUserService
 */
@Service
public class OauthUserServiceImpl
        extends AbstractService<OauthUser, OauthUserRepository>
        implements OauthUserService {

    public OauthUserServiceImpl(OauthUserRepository repository) {
        super(repository);
    }

    @Override
    public OauthUser getByOauthId(String oauthId) {
        return repository.findByOauthId(oauthId);
    }
}
