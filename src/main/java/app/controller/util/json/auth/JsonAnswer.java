package app.controller.util.json.auth;


/**
 * A GSON template for parsing json answer on the attempt of authorising
 */
public class JsonAnswer {

    /**
     * True if authorisation was allowed
     */
    private boolean accepted = false;

    /**
     * Contains information why authorisation was rejected
     */
    private String msg = "";

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
