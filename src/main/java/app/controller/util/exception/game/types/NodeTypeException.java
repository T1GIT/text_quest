package app.controller.util.exception.game.types;

import app.controller.util.exception.game.GameException;
import app.database.model.node.Node;
import app.database.util.AbstractModel;


/**
 * Exception throwing if JWT was missed in the cookie
 */
public class NodeTypeException extends GameException {

    public NodeTypeException(Node node) {
        super("Exception while processing " + node);
    }

    public NodeTypeException(Class<? extends Node> nodeClass) {
        super("Exception while processing node of type " + nodeClass);
    }
}
