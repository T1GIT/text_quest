package app.controller.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Translates Java object into a JavaScript object in the text representation.
 */
@Deprecated
public abstract class ObjectParser {

    /**
     * The main parser, only one, public available.
     * Is called by default and calls recursively parsers for another object types.
     *
     * @param object for parsing
     * @return object parsed to string
     */
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

    /**
     * Parses map to JS map, represented in string.
     *
     * @param map object to parse
     * @param <K> key type
     * @param <V> value type
     * @return parsed map
     */
    private static <K, V> String parse(Map<K, V> map) {
        List<String> items = new LinkedList<>();
        map.forEach((k, v) -> items.add(parse(k) + ":" + parse(v)));
        return "{" + String.join(",", items) + "}";
    }

    /**
     * Parses list to JS list, represented in string.
     *
     * @param list object to parse
     * @param <T>  type of inner objects
     * @return parsed list
     */
    private static <T> String parse(List<T> list) {
        List<String> items = new LinkedList<>();
        list.forEach((v) -> items.add(parse(v)));
        return "[" + String.join(",", items) + "]";
    }

    /**
     * Parses set to JS set, represented in string.
     *
     * @param set object to parse
     * @param <T> type of inner objects
     * @return parsed set
     */
    private static <T> String parse(Set<T> set) {
        List<String> items = new LinkedList<>();
        set.forEach((v) -> items.add(parse(v)));
        return "new Set([" + String.join(",", items) + "])";
    }

    /**
     * Parses array to JS list, represented in string.
     *
     * @param array object to parse
     * @param <T>   type of inner objects
     * @return parsed array
     */
    private static <T> String parse(T[] array) {
        List<String> items = new LinkedList<>();
        for (T el : array) items.add(parse(el));
        return "[" + String.join(",", items) + "]";
    }
}
