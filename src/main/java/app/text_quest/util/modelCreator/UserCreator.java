package app.text_quest.util.modelCreator;

import app.text_quest.model.Psw;
import app.text_quest.model.User;

public class UserCreator {
    public static User create() {
        User user = new User();
        user.setEmail("");
        user.setName("");
        user.setPsw(PswCreator.create());
        return user;
    }
}
