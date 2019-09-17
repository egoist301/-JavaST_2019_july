package by.training.catalog.bean;

public enum PlasticColor {
    COLOR("color"),
    WHITE("white"),
    BLACK("black");
    private String value;

    PlasticColor(final String valueNew) {
        value = valueNew;
    }

    public String getValue() {
        return value;
    }

    public static PlasticColor fromValue(final String v)
            throws UnknownTypeException {
        for (PlasticColor c : PlasticColor.values()) {
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
