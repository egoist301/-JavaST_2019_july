package by.training.catalog.bean;

public enum Manufacturer {
    XIAOMI("xiaomi"),
    MOYU("moyu"),
    GAN("gan"),
    SHENGSHOU("shengshou"),
    YUXIN("yuxin"),
    RUBIKS("rubiks"),
    MOFFANGE("moffange"),
    V_CUBE("v-cube"),
    DAYAN("dayan");
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
