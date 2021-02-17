package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Psw;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class PswFactory extends AbstractModelFactory<Psw> {
    private final String hash;
    private final String salt;

    public PswFactory() {
        this.hash = "pswHash";
        this.salt = "pswSalt";
    }

    public Psw create() {
        Psw psw = new Psw();
        psw.setHash(this.hash);
        psw.setSalt(this.salt);
        return psw;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }
}
