package app.text_quest.database.util.enums;

public enum Color {
    RED(""),
    BLUE(""),
    GREEN(""),
    YELLOW(""),
    PURPLE("");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Color{" +
                "value='" + value + '\'' +
                '}';
    }
}
