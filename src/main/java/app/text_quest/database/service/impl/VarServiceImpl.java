package app.text_quest.database.service.impl;

import app.text_quest.database.model.Var;
import app.text_quest.database.repo.VarRepository;
import app.text_quest.database.service.VarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class VarServiceImpl implements VarService {
    private final VarRepository varRepository;

    public VarServiceImpl(VarRepository varRepository) {
        this.varRepository = varRepository;
    }

    @Override
    public Var addVar(Var var) {
        return varRepository.saveAndFlush(var);
    }

    @Override
    public void delete(Var var) {
        varRepository.delete(var);
    }

    @Override
    public Var editVar(Var var) {
        return varRepository.saveAndFlush(var);
    }

    @Override
    public Var getByName(String name) {
        return varRepository.findByNameIs(name);
    }

    @Override
    public List<Var> getAll() {
        return varRepository.findAll();
    }
}
