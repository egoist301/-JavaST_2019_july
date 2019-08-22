package by.training.composite.service.locale;

import by.training.composite.bean.ResourceManager;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Resource handler.
 */
public class ResourceHandler {
    /**
     * Locale map with key - language and value - locale classes.
     */
    private static final Map<String, Locale> LOCALE_MAP = new HashMap<>();

    /**
     * Locale text menu.
     *
     * @param key key.
     */
    public void locale(final String key) {
        LOCALE_MAP.put("english", new Locale("en", "US"));
        LOCALE_MAP.put("russian", new Locale("ru", "RU"));
        LOCALE_MAP.put("belorussian", new Locale("be", "BY"));
        Locale locale = LOCALE_MAP.get(key);
        ResourceManager.INSTANCE.changeResource(locale);
    }
}
