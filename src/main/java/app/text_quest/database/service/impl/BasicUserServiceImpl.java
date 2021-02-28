package app.text_quest.database.service.impl;

import app.text_quest.database.model.user.types.BasicUser;
import app.text_quest.database.repo.BasicUserRepository;
import app.text_quest.database.service.BasicUserService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BasicUserServiceImpl implements BasicUserService {

    private final BasicUserRepository basicUserRepository;

    public BasicUserServiceImpl(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    @Override
    public BasicUser addBasicUser(BasicUser basicUser) {
        return basicUserRepository.saveAndFlush(basicUser);
    }

    @Override
    public void delete(BasicUser basicUser) {
        basicUserRepository.delete(basicUser);
    }

    @Override
    public BasicUser getByMail(String mail) {
        return basicUserRepository.findByMail(mail);
    }

    @Override
    public BasicUser editBasicUser(BasicUser basicUser) {
        return basicUserRepository.saveAndFlush(basicUser);
    }

    @Override
    public List<BasicUser> getAll() {
        return basicUserRepository.findAll();
    }
}
