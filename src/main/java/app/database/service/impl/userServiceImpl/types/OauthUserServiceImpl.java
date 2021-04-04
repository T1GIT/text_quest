package app.database.service.impl.userServiceImpl.types;

import app.database.model.user.types.OauthUser;
import app.database.repo.userRepository.types.OauthUserRepository;
import app.database.service.userService.types.OauthUserService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;


/**
 * @see app.database.service.userService.types.OauthUserService
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
