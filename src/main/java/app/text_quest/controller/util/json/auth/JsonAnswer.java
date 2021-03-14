package app.text_quest.controller.util.json.auth;

public class JsonAnswer {

    private boolean accepted = false;
    private String msg = "";

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
