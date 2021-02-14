package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Branch;
import app.text_quest.model.node.Node;
import app.text_quest.model.node.types.Fork;
import app.text_quest.util.modelFactory.AbstractModelFactory;
import app.text_quest.util.modelFactory.types.node.NodeFactory;
import app.text_quest.util.modelFactory.types.node.types.ForkFactory;

public class BranchFactory extends AbstractModelFactory<Branch> {
    private final static ForkFactory forkFactory = new ForkFactory();
    private final static NodeFactory nodeFactory = new NodeFactory();
    private final Fork fork = forkFactory.create();
    private final Node node = nodeFactory.create();

    public Branch create() {
        Branch branch = new Branch();
        branch.setFork(forkFactory.create());
        branch.setNextNode(nodeFactory.create());
        return branch;
    }

    public Fork getFork() {
        return fork;
    }

    public Node getNode() {
        return node;
    }
}
