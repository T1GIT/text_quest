package app.text_quest.database.util.enums;


/**
 <h2> An enum {@link Color}

 <p> Holds color of the user's theme
 */
public enum Color {
    RED(""),
    BLUE(""),
    GREEN(""),
    YELLOW(""),
    PURPLE("");

    /**
     Contains the hex code of the current color.
     */
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
