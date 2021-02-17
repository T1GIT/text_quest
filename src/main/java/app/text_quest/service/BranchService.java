package app.text_quest.service;

import app.text_quest.model.Branch;
import app.text_quest.model.node.types.Fork;

import java.util.List;

@Deprecated
public interface BranchService {
    Branch addBranch(Branch branch);

    void delete(Branch branch);

    List<Branch> getByFork(Fork fork);

    Branch editBranch(Branch branch);

    List<Branch> getAll();
}
