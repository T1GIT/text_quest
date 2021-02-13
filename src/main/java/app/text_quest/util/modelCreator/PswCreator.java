package app.text_quest.util.modelCreator;

import app.text_quest.model.Psw;

public class PswCreator {
    public static Psw create() {
        Psw psw = new Psw();
        psw.setHash("");
        psw.setSalt("");
        return psw;
    }
}
