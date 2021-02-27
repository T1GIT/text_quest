package app.text_quest.database.service;

import app.text_quest.database.model.Branch;
import app.text_quest.database.model.node.types.Fork;

import java.util.List;

@Deprecated
public interface BranchService {
    Branch addBranch(Branch branch);

    void delete(Branch branch);

    List<Branch> getByFork(Fork fork);

    Branch editBranch(Branch branch);

    List<Branch> getAll();
}
