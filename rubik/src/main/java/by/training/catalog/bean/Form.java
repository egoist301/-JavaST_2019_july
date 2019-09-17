package by.training.catalog.bean;

public enum Form {
    PYRAMID("pyramid"),
    MEGAMINX("megaminx"),
    CUBOID("cuboid"),
    SKEWB("skewb"),
    SQUARE("square"),
    CUBE("cube");
    private String value;

    Form(final String valueNew) {
        value = valueNew;
    }

    public String getValue() {
        return value;
    }

    public static Form fromValue(final String v)
            throws UnknownTypeException {
        for (Form c : Form.values()) {
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
