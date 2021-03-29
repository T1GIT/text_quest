package app.text_quest.util;

/**
 * A storage for constants
 * <p>
 * Must be extended by a class, containing constants. Children classes must have a {@code final} modifier.
 * <p>
 * Every constant must by initialised in this way:
 * <p>
 * {@code public final static [String/int] [NAME IN UPPER CASE] = [value] }
 * <p>
 * Call to the constants looks like:
 * <p>
 * {@code AbstractConstant's child.[CONSTANT_NAME]}
 */
public abstract class AbstractConstant {

    /**
     * Protected constructor for locking
     * constants class from creating its object.
     */
    protected AbstractConstant() {

    }
}
