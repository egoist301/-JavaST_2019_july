package by.training.greenhouse.bean;

/**
 * Multiplying.
 */
public enum Multiplying {
    /**
     * Seed.
     */
    SEED("seed"),
    /**
     * Leaves.
     */
    LEAVES("leaves"),
    /**
     * Cuttings.
     */
    CUTTINGS("cuttings");
    /**
     * Value.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param v value.
     */
    Multiplying(final String v) {
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
     * Getter multiplying from value.
     *
     * @param v value.
     * @return multiplying.
     * @throws UnknownTypeException custom exception.
     */
    public static Multiplying fromValue(final String v)
            throws UnknownTypeException {
        for (Multiplying c : Multiplying.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new UnknownTypeException(v);
    }
}
