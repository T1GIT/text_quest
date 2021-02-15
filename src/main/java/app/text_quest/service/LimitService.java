package app.text_quest.service;



import app.text_quest.model.Branch;
import app.text_quest.model.Limit;
import app.text_quest.model.Var;

import java.util.List;

@Deprecated
public interface LimitService {
    Limit addLimit(Limit limit);
    void delete(Limit limit);
    List<Limit> getByBranch(Branch branch);
    Limit getByBranchAndVar(Branch branch, Var var);
    Limit editLimit(Limit limit);
    List<Limit> getAll();
}
