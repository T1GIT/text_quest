package app.controller.util;

import app.controller.util.exception.game.types.FinishException;
import app.controller.util.exception.game.types.GoingThrowQuestionWIithoutAnswerException;
import app.controller.util.exception.game.types.ModelNotFoundException;
import app.controller.util.exception.game.types.NodeTypeException;
import app.database.model.Branch;
import app.database.model.Limit;
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
            List<State> stateList = new LinkedList<>();
            stateService.getByUser(user)
                    .forEach(state -> stateList.add((State) Hibernate.unproxy(state)));
            Branch branch = chooseBranch(((Fork) node).getBranches(), stateList);
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

    private static Branch chooseBranch(List<Branch> branchList, List<State> stateList) {
        System.out.println(branchList);
        System.out.println(stateList);
        Branch branch1 = branchList.parallelStream()
                .filter(branch -> checkLimits(stateList, branch.getLimits()))
                .findAny().orElseThrow(ModelNotFoundException::new);
        System.out.println(branch1);
        return branch1;
    }

    private static boolean checkLimits(List<State> stateList, List<Limit> limitList) {
        for (Limit limit : limitList) {
            State state = stateList.parallelStream()
                    .filter(stateItem -> limit.getVar().getId() == stateItem.getVar().getId())
                    .findAny().orElseThrow(ModelNotFoundException::new);
            int val = state.getVal();
            if (limit.getMin() != null && limit.getMin() > val) return false;
            if (limit.getMax() != null && limit.getMax() < val) return false;
            if (limit.getEqual() != null && limit.getEqual() != val) return false;
        }
        return true;
    }
}
