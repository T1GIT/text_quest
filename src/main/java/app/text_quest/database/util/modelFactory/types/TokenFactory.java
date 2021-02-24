package app.text_quest.database.util.modelFactory.types;

import app.text_quest.database.model.Token;
import app.text_quest.database.util.modelFactory.AbstractModelFactory;


@Deprecated
public class TokenFactory extends AbstractModelFactory<Token> {

    public TokenFactory() {
    }

    public Token create() {
        return new Token();
    }
}
