package app.text_quest.database.service.impl;

import app.text_quest.database.model.Branch;
import app.text_quest.database.model.Limit;
import app.text_quest.database.model.Var;
import app.text_quest.database.repo.LimitRepository;
import app.text_quest.database.service.LimitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Deprecated
@Service
@Transactional
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;

    public LimitServiceImpl(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @Override
    public Limit addLimit(Limit limit) {
        return limitRepository.saveAndFlush(limit);
    }

    @Override
    public void delete(Limit limit) {
        limitRepository.delete(limit);
    }

    @Override
    public List<Limit> getByBranch(Branch branch) {
        return limitRepository.findByBranchId(branch.getId());
    }

    @Override
    public Limit getByBranchAndVar(Branch branch, Var var) {
        return limitRepository.findByBranchIdAndVarId(branch.getId(), var.getId());
    }

    @Override
    public Limit editLimit(Limit limit) {
        return limitRepository.saveAndFlush(limit);
    }

    @Override
    public List<Limit> getAll() {
        return limitRepository.findAll();
    }
}
