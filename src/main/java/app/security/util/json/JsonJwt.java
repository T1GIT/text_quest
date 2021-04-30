package app.security.util.json;

import app.database.model.user.User;

public class JsonJwt {
    private User user;
    private String socketId;

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
