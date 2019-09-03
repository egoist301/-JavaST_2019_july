package by.training.greenhouse.bean;

/**
 * Flower name tag.
 */
public enum FlowerNameTag {
    /**
     * Flower name.
     */
    NAME("name"),
    /**
     * Origin.
     */
    ORIGIN("origin"),
    /**
     * Stem color.
     */
    STEM_COLOR("stem-color"),
    /**
     * Height.
     */
    HEIGHT("height"),
    /**
     * Temperature.
     */
    TEMPERATURE("temperature"),
    /**
     * Discovery date.
     */
    DISCOVERY_DATE("discovery-date"),
    /**
     * Soil.
     */
    SOIL("soil"),
    /**
     * Multiplying.
     */
    MULTIPLYING("multiplying"),
    /**
     * Watering.
     */
    WATERING("watering"),
    /**
     * Living flower.
     */
    LIVING_FLOWER("living-flower"),
    /**
     * Artificial flower.
     */
    ARTIFICIAL_FLOWER("artificial-flower"),
    /**
     * Material.
     */
    MATERIAL("material"),
    /**
     * Id.
     */
    ID("id"),
    /**
     * Photophilous.
     */
    PHOTOPHILOUS("is-photophilous"),
    /**
     * Medicinal.
     */
    MEDICINAL("is-medicinal");
    /**
     * Value.
     */
    private String value;

    /**
     * Constructor.
     *
     * @param valueNew value.
     */
    FlowerNameTag(final String valueNew) {
        value = valueNew;
    }

    /**
     * Getter.
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter tag from value.
     *
     * @param valueNew value.
     * @return tag.
     */
    public static FlowerNameTag fromValue(final String valueNew) {
        for (FlowerNameTag tag : FlowerNameTag.values()) {
            if (tag.value.equals(valueNew)) {
                return tag;
            }
        }
        throw new IllegalArgumentException(valueNew);
    }
}

