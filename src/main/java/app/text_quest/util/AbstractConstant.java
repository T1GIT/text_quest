package app.text_quest.util;

import java.lang.reflect.Field;
import java.util.HashMap;

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

    /**
     * Makes an array of all constants names.
     *
     * @param constantClass source of constants
     * @param <T>           type of the class
     * @return array of constants names
     */
    public static <T> String[] getNames(Class<T> constantClass) {
        Field[] fields = constantClass.getFields();
        String[] names = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            names[i] = fields[i].getName();
        }
        return names;
    }

    /**
     * Makes an array of all constants values.
     *
     * @param constantClass source of constants
     * @param <T>           type of the class
     * @return array of constants values
     */
    public static <T> Object[] getValues(Class<T> constantClass) {
        Field[] fields = constantClass.getFields();
        Object[] values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            try {
                values[i] = fields[i].get(null);
            } catch (IllegalAccessException ignored) {
            }
        }
        return values;
    }

    /**
     * Makes a map of all constants.
     *
     * @param constantClass source of constants
     * @param <T>           type of the class
     * @return map of constants
     */
    public static <T> HashMap<String, Object> getMap(Class<T> constantClass) {
        Field[] fields = constantClass.getFields();
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            try {
                map.put(field.getName(), field.get(null));
            } catch (IllegalAccessException ignored) {
            }
        }
        return map;
    }

    /**
     * Gets value of the specified field from the constants class.
     *
     * @param constantClass source of constants
     * @param name          for searching
     * @param <T>           type of the class
     * @return value of the field or null if field with given name not exist
     */
    public static <T> Object getByName(Class<T> constantClass, String name) {
        try {
            return constantClass.getField(name);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
