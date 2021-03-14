package app.text_quest.controller.util;

import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class ObjectParser {

    public static String parse(Object object) {
        if (object instanceof Map) return parse((Map) object);
        else if (object instanceof List) return parse((List) object);
        else if (object instanceof Object[]) return parse((Object[]) object);
        else if (object instanceof Set) return parse((Set) object);
        else if (object instanceof Boolean) return object.toString();
        else if (object instanceof String) return String.format("\"%s\"",
                ((String) object).replaceAll("\"", "\\\""));
        else return parse(String.valueOf(object));
    }

    private static <K, V> String parse(Map<K, V> map) {
        StringBuilder builder = new StringBuilder("{");
        map.forEach((k, v) -> {
            builder.append(String.format(
                    "%s:%s,",
                    parse(k),
                    parse(v)));
        });
        builder.append("}");
        return builder.toString();
    }

    private static <T> String parse(List<T> list) {
        StringBuilder builder = new StringBuilder("[");
        list.forEach((el) -> {
            builder.append(String.format("%s,", parse(el)));
        });
        builder.append("]");
        return builder.toString();
    }


    private static <T> String parse(Set<T> list) {
        StringBuilder builder = new StringBuilder("new Set([");
        list.forEach((el) -> {
            builder.append(String.format("%s,", parse(el)));
        });
        builder.append("])");
        return builder.toString();
    }

    private static <T> String parse(T[] array) {
        StringBuilder builder = new StringBuilder("[");
        for (T el : array) {
            builder.append(String.format("%s,", parse(el)));
        }
        builder.append("]");
        return builder.toString();
    }
}
