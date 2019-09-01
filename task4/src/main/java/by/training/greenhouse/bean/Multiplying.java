package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Multiplying")
@XmlEnum
public enum Multiplying {
    @XmlEnumValue("seed")
    SEED("seed"),
    @XmlEnumValue("leaves")
    LEAVES("leaves"),
    @XmlEnumValue("cuttings")
    CUTTINGS("cuttings");
    private final String value;

    Multiplying(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Multiplying fromValue(String v) {
        for (Multiplying c: Multiplying.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
