package app.text_quest.controller.util;

import java.util.LinkedList;
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
        List<String> items = new LinkedList<>();
        map.forEach((k, v) -> items.add(parse(k) + ":" + parse(v)));
        return "{" + String.join(",", items) + "}";
    }

    private static <T> String parse(List<T> list) {
        List<String> items = new LinkedList<>();
        list.forEach((v) -> items.add(parse(v)));
        return "[" + String.join(",", items) + "]";
    }


    private static <T> String parse(Set<T> set) {
        List<String> items = new LinkedList<>();
        set.forEach((v) -> items.add(parse(v)));
        return "new Set([" + String.join(",", items) + "])";
    }

    private static <T> String parse(T[] array) {
        List<String> items = new LinkedList<>();
        for (T el : array) items.add(parse(el));
        return "[" + String.join(",", items) + "]";
    }
}
