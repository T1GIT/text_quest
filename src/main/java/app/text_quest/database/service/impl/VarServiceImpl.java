package app.text_quest.database.service.impl;

import app.text_quest.database.model.Var;
import app.text_quest.database.repo.VarRepository;
import app.text_quest.database.service.VarService;
import app.text_quest.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 @see app.text_quest.database.service.VarService */
@Service
@Transactional
public class VarServiceImpl
        extends AbstractService<Var, VarRepository>
        implements VarService {

    public VarServiceImpl(VarRepository repository) {
        super(repository);
    }

    @Override
    public Var getByName(String name) {
        return repository.findByName(name);
    }
}
