package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Psw;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class PswFactory extends AbstractModelFactory<Psw> {

    private final byte[] hash;
    private final byte[] salt;

    public PswFactory() {
        this.hash = new byte[]{0, 0, 0};
        this.salt = new byte[]{0, 0, 0};
    }

    public Psw create() {
        Psw psw = new Psw();
        psw.setHash(this.hash);
        psw.setSalt(this.salt);
        return psw;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }
}
