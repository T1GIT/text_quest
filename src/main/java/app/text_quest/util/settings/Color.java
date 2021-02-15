package app.text_quest.util.settings;

public enum Color {
    RED (""),
    BLUE (""),
    GREEN (""),
    YELLOW (""),
    PURPLE ("");

    private String value;

    Color (String value) {
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
