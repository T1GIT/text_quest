package app.controller.util.exception.game.types;

import app.controller.util.exception.game.GameException;
import app.controller.util.exception.missedToken.MissedTokenException;
import app.database.util.AbstractModel;


/**
 * Exception throwing if JWT was missed in the cookie
 */
public class ModelNotFoundException extends GameException {

}
