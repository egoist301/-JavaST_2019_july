package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Soil.
 */
@XmlType(name = "Soil")
@XmlEnum
public enum Soil {
    /**
     * Podzolic.
     */
    @XmlEnumValue("podzolic")
    PODZOLIC("podzolic"),
    /**
     * Ground.
     */
    @XmlEnumValue("ground")
    GROUND("ground"),
    /**
     * Sod podzolic.
     */
    @XmlEnumValue("sod-podzolic")
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
     */
    public static Soil fromValue(final String v) {
        for (Soil c : Soil.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
