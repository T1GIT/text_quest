package app.text_quest.database.service.impl;

import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.repo.OauthUserRepository;
import app.text_quest.database.service.OauthUserService;
import app.text_quest.database.util.AbstractServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class OauthUserServiceImpl
        extends AbstractServiceImpl<OauthUser, OauthUserRepository>
        implements OauthUserService {

    public OauthUserServiceImpl(OauthUserRepository repository) {
        super(repository);
    }

    @Override
    public OauthUser getByOauthId(String oauthId) {
        return repository.findByOauthId(oauthId);
    }
}
