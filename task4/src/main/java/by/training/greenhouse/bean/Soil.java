package by.training.greenhouse.bean;

/**
 * Soil.
 */
public enum Soil {
    /**
     * Podzolic.
     */
    PODZOLIC("podzolic"),
    /**
     * Ground.
     */
    GROUND("ground"),
    /**
     * Sod podzolic.
     */
    SOD_PODZOLIC("sod-podzolic");
    /**
     * Value.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param v value.
     */
    Soil(final String v) {
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
     * Getter soil from value.
     *
     * @param v value.
     * @return soil.
     * @throws UnknownTypeException custom exception.
     */
    public static Soil fromValue(final String v) throws UnknownTypeException {
        for (Soil c : Soil.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new UnknownTypeException(v);
    }
}
