package app.text_quest.database.service.impl;

import app.text_quest.database.model.Branch;
import app.text_quest.database.model.node.types.Fork;
import app.text_quest.database.repo.BranchRepository;
import app.text_quest.database.service.BranchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Deprecated
@Service
@Transactional
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch addBranch(Branch branch) {
        return this.branchRepository.saveAndFlush(branch);
    }

    @Override
    public void delete(Branch branch) {
        this.branchRepository.delete(branch);
    }

    @Override
    public List<Branch> getByFork(Fork fork) {
        return this.branchRepository.findByForkId(fork.getId());
    }

    @Override
    public Branch editBranch(Branch branch) {
        return this.branchRepository.saveAndFlush(branch);
    }

    @Override
    public List<Branch> getAll() {
        return this.branchRepository.findAll();
    }
}
