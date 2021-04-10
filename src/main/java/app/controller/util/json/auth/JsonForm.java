package app.controller.util.json.auth;


/**
 * A GSON template for extracting user creditionals from the form's request
 */
public class JsonForm {

    /**
     * Field email in the form
     */
    private String mail;
    /**
     * Field password in the form
     */
    private String psw;

    public String getMail() {
        return mail;
    }

    public String getPsw() {
        return psw;
    }
}
