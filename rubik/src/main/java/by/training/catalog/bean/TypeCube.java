package by.training.catalog.bean;

public enum TypeCube {
    PYRAMID("pyramid"),
    MEGAMINX("megaminx"),
    CUBOID("cuboid"),
    SKEWB("skewb"),
    SQUARE("square"),
    CUBE("cube");
    private String value;

    TypeCube(final String valueNew) {
        value = valueNew;
    }

    public String getValue() {
        return value;
    }

    public static TypeCube fromValue(final String v)
            throws UnknownTypeException {
        for (TypeCube c : TypeCube.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new UnknownTypeException(v);
    }
}
