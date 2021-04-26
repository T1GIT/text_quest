package app.controller.util.json.game;

import app.database.model.node.types.LinkedNode.LinkedNode;

import java.io.Serializable;

public class InitialResponse implements Serializable {

    private String socketId;
    private LinkedNode lastNode;

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public void setLastNode(LinkedNode lastNode) {
        this.lastNode = lastNode;
    }

    public String getSocketId() {
        return socketId;
    }

    public LinkedNode getLastNode() {
        return lastNode;
    }
}
