package app.security.util;

import java.util.regex.Pattern;


/**
 * Provides static methods to check if
 * email or password string is valid
 */
public abstract class Validator {

    /**
     * RegExp for validating email address
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    /**
     * RegExp for validating password
     */
    private static final Pattern PSW_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-zA-Zа-яА-Я]).{8,}$");

    /**
     * Checks if email is valid
     * <p>
     * Email must:
     * <ul>
     * <li> contain only latin symbols and numerics
     * <li> contain al least 3 domains, separated by point
     * </ul>
     *
     * @param mail email address string
     * @return true if email meets the requirements
     */
    public static boolean mail(final String mail) {
        return EMAIL_PATTERN.matcher(mail).matches() && mail.length() <= 254;
    }

    /**
     * Checks if password is valid
     * <p>
     * Password must:
     * <ul>
     * <li> contain numerics
     * <li> contain latin or cyrillic symbols
     * <li> be longer then 7 symbols
     * <li> be shorter then 121 symbols
     * </ul>
     *
     * @param psw raw password string
     * @return true if password meets the requirements
     */
    public static boolean psw(final String psw) {
        return PSW_PATTERN.matcher(psw).matches() && psw.length() <= 120;
    }

}