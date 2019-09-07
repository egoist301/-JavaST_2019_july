package by.training.greenhouse.bean;

/**
 * Color enum.
 */
public enum Color {
    /**
     * Green color.
     */
    GREEN("green"),
    /**
     * Red color.
     */
    RED("red"),
    /**
     * Yellow color.
     */
    YELLOW("yellow");
    /**
     * Value.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param v value.
     */
    Color(final String v) {
        value = v;
    }

    /**
     * Getter.
     *
     * @return value.
     */
    public String value() {
        return value;
    }

    /**
     * Getter color from value.
     *
     * @param v value.
     * @return color.
     * @throws UnknownTypeException custom exception.
     */
    public static Color fromValue(final String v) throws UnknownTypeException {
        for (Color c : Color.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new UnknownTypeException(v);
    }
}
