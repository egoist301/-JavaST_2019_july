package by.training.composite.bean;

import by.training.composite.service.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource manager. Singleton.
 */
public enum ResourceManager {
    /**
     * Resource bundle.
     */
    INSTANCE;
    /**
     * Resource bundle.
     */
    private ResourceBundle resourceBundle;

    /**
     * Default constructor.
     */
    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(Configuration.RESOURCE_NAME,
                Locale.getDefault());
    }

    /**
     * Change resource for locale.
     *
     * @param locale locale.
     */
    public void changeResource(final Locale locale) {
        resourceBundle = ResourceBundle.getBundle(Configuration.RESOURCE_NAME,
                locale);
    }

    /**
     * Getter.
     *
     * @param key key string.
     * @return string.
     */
    public String getString(final String key) {
        return resourceBundle.getString(key);
    }
}
