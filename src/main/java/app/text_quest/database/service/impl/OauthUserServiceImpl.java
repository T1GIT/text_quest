package app.text_quest.database.service.impl;

import app.text_quest.database.model.user.types.OauthUser;
import app.text_quest.database.repo.OauthUserRepository;
import app.text_quest.database.service.OauthUserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OauthUserServiceImpl implements OauthUserService {

    private final OauthUserRepository oauthUserRepository;

    public OauthUserServiceImpl(OauthUserRepository oauthUserRepository) {
        this.oauthUserRepository = oauthUserRepository;
    }

    @Override
    public OauthUser addOauthUser(OauthUser oauthUser) {
        return oauthUserRepository.saveAndFlush(oauthUser);
    }

    @Override
    public void delete(OauthUser oauthUser) {
        oauthUserRepository.delete(oauthUser);
    }

    @Override
    public OauthUser getByOauthId(String oauthId) {
        return oauthUserRepository.findByOauthId(oauthId);
    }

    @Override
    public OauthUser editOauthUser(OauthUser oauthUser) {
        return oauthUserRepository.saveAndFlush(oauthUser);
    }

    @Override
    public List<OauthUser> getAll() {
        return oauthUserRepository.findAll();
    }
}
