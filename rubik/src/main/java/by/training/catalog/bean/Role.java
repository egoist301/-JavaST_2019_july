package by.training.catalog.bean;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");
    private String value;

    Role(final String valueNew) {
        value = valueNew;
    }

    public String getValue() {
        return value;
    }

    public static Role fromValue(final String v)
            throws UnknownTypeException {
        for (Role c : Role.values()) {
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
