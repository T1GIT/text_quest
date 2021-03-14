package app.text_quest.security.util;

import java.util.regex.Pattern;


public abstract class Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private static final Pattern PSW_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-zA-Zа-яА-Я]).{8,}$");

    public static boolean mail(final String mail) {
        return EMAIL_PATTERN.matcher(mail).matches() && mail.length() <= 254;
    }

    public static boolean psw(final String psw) {
        return PSW_PATTERN.matcher(psw).matches() && psw.length() <= 120;
    }

}