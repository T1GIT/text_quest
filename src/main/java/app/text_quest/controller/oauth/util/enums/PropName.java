package app.text_quest.controller.oauth.util.enums;


public enum PropName {
    CLIENT_ID, CLIENT_SECRET, DOMAIN_AUTH, DOMAIN_TOKEN, DOMAIN_ID, SCOPE;

    public String lowName() {
        return this.name().toLowerCase();
    }
}
