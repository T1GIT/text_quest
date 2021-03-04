package app.text_quest.controller.oauth.util.enums;


public enum Provider {
    YANDEX, GOOGLE, VK;


    public String lowName() {
        return this.name().toLowerCase();
    }
}
