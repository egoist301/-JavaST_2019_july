package by.training.catalog.bean;

public enum Manufacturer {
    DAYAN("dayan"),
    MOYU("moyu"),
    GAN("gan"),
    RUBIKS("rubiks"),
    SHENGSHOU("shengshou"),
    XIAOMI("xiaomi"),
    V_CUBE("v-cube"),
    YUXIN("yuxin"),
    MOFFANGE("moffange"),
    YJ("yj");
    private String value;

    Manufacturer(final String valueNew) {
        value = valueNew;
    }

    public String getValue() {
        return value;
    }

    public static Manufacturer fromValue(final String v)
            throws UnknownTypeException {
        for (Manufacturer c : Manufacturer.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new UnknownTypeException(v);
    }

    public Integer getIdentity() {
        return ordinal();
    }
}
