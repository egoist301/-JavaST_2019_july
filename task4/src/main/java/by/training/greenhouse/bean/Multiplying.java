package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Multiplying.
 */
@XmlType(name = "Multiplying")
@XmlEnum
public enum Multiplying {
    /**
     * Seed.
     */
    @XmlEnumValue("seed")
    SEED("seed"),
    /**
     * Leaves.
     */
    @XmlEnumValue("leaves")
    LEAVES("leaves"),
    /**
     * Cuttings.
     */
    @XmlEnumValue("cuttings")
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
     */
    public static Multiplying fromValue(final String v) {
        for (Multiplying c : Multiplying.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
