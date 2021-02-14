package app.text_quest.service.impl;

import app.text_quest.model.Psw;
import app.text_quest.model.User;
import app.text_quest.repo.PswRepository;
import app.text_quest.service.PswService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PswServiceImpl implements PswService {
    private final PswRepository pswRepository;

    public PswServiceImpl(PswRepository pswRepository) {
        this.pswRepository = pswRepository;
    }

    @Override
    public Psw addPsw(Psw psw) {
        return this.pswRepository.saveAndFlush(psw);
    }

    @Override
    public void delete(Psw psw) {
        this.pswRepository.delete(psw);
    }

    @Override
    public Psw getByUser(User user) {
        return this.pswRepository.findByUserId(user.getId());
    }

    @Override
    public Psw editPsw(Psw psw) {
        return this.pswRepository.saveAndFlush(psw);
    }

    @Override
    public List<Psw> getAll() {
        return this.pswRepository.findAll();
    }
}
