package app.text_quest.util;

import java.util.Arrays;


public class ExceptionLogger {

    private static final int MAX_LENGTH = 100;

    public static String getExceptionLog(Exception e) {
        String trace = Arrays.toString(e.getStackTrace()).substring(1);
        return String.format("%-20s : %s",
                e.getClass(),
                trace.substring(0, Math.min(MAX_LENGTH, trace.length()))
        );
    }
}
