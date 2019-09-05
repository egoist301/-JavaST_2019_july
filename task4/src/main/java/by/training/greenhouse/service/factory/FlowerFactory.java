package by.training.greenhouse.service.factory;

import by.training.greenhouse.service.parser.AbstractBuilder;
import by.training.greenhouse.service.parser.FlowerDOMBuilder;
import by.training.greenhouse.service.parser.FlowerSAXBuilder;
import by.training.greenhouse.service.parser.FlowerSTaXBuilder;

import java.util.EnumMap;
import java.util.Map;

/**
 * Flower factory.
 */
public final class FlowerFactory {
    /**
     * Builder type.
     */
    private enum BuilderType {
        /**
         * DOM parser.
         */
        DOM,
        /**
         * SAX parser.
         */
        SAX,
        /**
         * STaX parser.
         */
        STAX
    }

    /**
     * Instance.
     */
    private static final FlowerFactory INSTANCE = new FlowerFactory();

    /**
     * Factory.
     */
    private final Map<BuilderType, AbstractBuilder> factory =
            new EnumMap<>(BuilderType.class);

    /**
     * Default constructor.
     */
    private FlowerFactory() {
        factory.put(BuilderType.DOM, new FlowerDOMBuilder());
        factory.put(BuilderType.SAX, new FlowerSAXBuilder());
        factory.put(BuilderType.STAX, new FlowerSTaXBuilder());
    }

    /**
     * Getter.
     *
     * @return singleton.
     */
    public static FlowerFactory getINSTANCE() {
        return INSTANCE;
    }

    /**
     * Create parser.
     *
     * @param parser string parser.
     * @return parser.
     */
    public AbstractBuilder createFlowerBuilder(final String parser) {
        BuilderType type = BuilderType.valueOf(parser.toUpperCase());
        return factory.get(type);
    }
}
