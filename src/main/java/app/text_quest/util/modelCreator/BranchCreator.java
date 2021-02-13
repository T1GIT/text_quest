package app.text_quest.util.modelCreator;

import app.text_quest.model.Branch;
import app.text_quest.model.Psw;
import app.text_quest.util.modelCreator.Node.NodeCreator;
import app.text_quest.util.modelCreator.Node.types.ForkCreator;

public class BranchCreator {
    public static Branch create() {
        Branch branch = new Branch();
        branch.setFork(ForkCreator.create());
        branch.setNextNode(NodeCreator.create());
        return branch;
    }
}
