package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Color")
@XmlEnum
public enum  Color {
    @XmlEnumValue("green")
    GREEN("green"),
    @XmlEnumValue("red")
    RED("red"),
    @XmlEnumValue("yellow")
    YELLOW("yellow");
    private final String value;

    Color(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Color fromValue(String v) {
        for (Color c: Color.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
