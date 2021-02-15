package app.text_quest.service.impl;

import app.text_quest.model.Branch;
import app.text_quest.model.Limit;
import app.text_quest.model.Var;
import app.text_quest.repo.LimitRepository;
import app.text_quest.service.LimitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
