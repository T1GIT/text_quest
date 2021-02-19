package app.text_quest.util;

import java.util.Arrays;


public class FileLogger {

    private static final int MAX_LENGTH = 100;

    public static String getExceptionLog(Exception e) {
        return String.format("%-20s : %s",
                e.getClass(),
                Arrays.toString(e.getStackTrace()).substring(1, MAX_LENGTH)
        );
    }
}
