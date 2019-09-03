package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Color enum.
 */
@XmlType(name = "Color")
@XmlEnum
public enum Color {
    /**
     * Green color.
     */
    @XmlEnumValue("green")
    GREEN("green"),
    /**
     * Red color.
     */
    @XmlEnumValue("red")
    RED("red"),
    /**
     * Yellow color.
     */
    @XmlEnumValue("yellow")
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
     */
    public static Color fromValue(final String v) {
        for (Color c : Color.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
