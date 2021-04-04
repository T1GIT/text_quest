package app.database.service.impl;

import app.database.model.Var;
import app.database.repo.VarRepository;
import app.database.service.VarService;
import app.database.util.abstractService.impl.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @see app.database.service.VarService
 */
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
