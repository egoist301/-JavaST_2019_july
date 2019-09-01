package by.training.greenhouse.bean;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Soil")
@XmlEnum
public enum Soil {
    @XmlEnumValue("podzolic")
    PODZOLIC("podzolic"),
    @XmlEnumValue("ground")
    GROUND("ground"),
    @XmlEnumValue("sod-podzolic")
    SOD_PODZOLIC("sod-podzolic");
    private final String value;

    Soil(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Soil fromValue(String v) {
        for (Soil c : Soil.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
