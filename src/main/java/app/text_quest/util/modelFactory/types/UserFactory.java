package app.text_quest.util.modelFactory.types;

import app.text_quest.model.Psw;
import app.text_quest.model.User;
import app.text_quest.util.modelFactory.AbstractModelFactory;

public class UserFactory extends AbstractModelFactory<User> {
    private final static PswFactory pswFactory = new PswFactory();
    private final String email = "test_email@mail.ru";
    private final String name = "user_name";
    private final Psw psw = pswFactory.create();

    @Override
    public User create() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPsw(pswFactory.create());
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Psw getPsw() {
        return psw;
    }
}
