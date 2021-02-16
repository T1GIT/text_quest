package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Psw;
import app.text_quest.model.User;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class PswFactory extends AbstractModelFactory<Psw> {
    private final static UserFactory userFactory = new UserFactory();
    private final String hash;
    private final String salt;
    private final User user = userFactory.create();

    public PswFactory() {
        this.hash = "pswHash";
        this.salt = "pswSalt";
    }

    public Psw create() {
        Psw psw = new Psw();
        psw.setHash(this.hash);
        psw.setSalt(this.salt);
        psw.setUser(userFactory.create());
        return psw;
    }

    public String getHash() {
        return hash;
    }

    public String getSalt() {
        return salt;
    }

    public User getUser() {
        return user;
    }
}
