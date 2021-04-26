package app.core;

import app.controller.util.exception.game.types.FinishException;
import app.controller.util.exception.game.types.GoingThrowQuestionWIithoutAnswerException;
import app.controller.util.exception.game.types.ModelNotFoundException;
import app.controller.util.exception.game.types.NodeTypeException;
import app.database.model.Branch;
import app.database.model.Condition;
import app.database.model.State;
import app.database.model.node.Node;
import app.database.model.node.types.Fork;
import app.database.model.node.types.LinkedNode.LinkedNode;
import app.database.model.user.User;
import app.database.service.NodeService;
import app.database.service.StateService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Tree {

    private final NodeService nodeService;
    private final StateService stateService;

    public Tree(NodeService nodeService, StateService stateService) {
        this.nodeService = nodeService;
        this.stateService = stateService;
    }


    public LinkedNode nextNode(Node node, User user) throws GoingThrowQuestionWIithoutAnswerException, FinishException {
        Node nextNode;
        if (node == null) {
            throw new FinishException();
        } else if (node.getClass() == Fork.class) {
            Branch branch = chooseBranch(((Fork) node).getBranches(), user);
            nextNode = branch.getNextNode();
        } else if (node instanceof LinkedNode) {
            nextNode = ((LinkedNode) node).getNextNode();
        } else {
            throw new NodeTypeException(node.getClass());
        }
        nextNode = (Node) Hibernate.unproxy(nodeService.getById(nextNode.getId()));
        if (nextNode.getClass() == Fork.class) {
            nextNode = nextNode(nextNode, user);
        }
        return (LinkedNode) nextNode;
    }

    private Branch chooseBranch(List<Branch> branchList, User user) {
        List<State> stateList = new LinkedList<>();
        stateService.getByUser(user)
                .forEach(state -> stateList.add((State) Hibernate.unproxy(state)));
        return branchList.parallelStream()
                .filter(branch -> checkLimits(stateList, branch.getLimits()))
                .findAny().orElseThrow(ModelNotFoundException::new);
    }

    private boolean checkLimits(List<State> stateList, List<Condition> conditionList) {
        for (Condition condition : conditionList) {
            State state = stateList.parallelStream()
                    .filter(stateItem -> condition.getVar().getId() == stateItem.getVar().getId())
                    .findAny().orElseThrow(ModelNotFoundException::new);
            int val = state.getVal();
            if (condition.getMin() != null && condition.getMin() > val) return false;
            if (condition.getMax() != null && condition.getMax() < val) return false;
            if (condition.getEqual() != null && condition.getEqual() != val) return false;
        }
        return true;
    }
}
