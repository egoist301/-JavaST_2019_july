package by.training.catalog.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeLocaleCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Map<String, String> lang = new HashMap<>();

    static {
        lang.put("ru", "ru_RU");
        lang.put("en", "en_US");
    }

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String langParam = request.getParameter("id");
        String locale = lang.get(langParam);
        if (locale == null) {
            return new Forward("index.html", true);
        } else {
            Cookie cookie = new Cookie("locale", locale);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            String page = request.getParameter("from");
            LOGGER.debug("From!!!!!!!1!!!  {}", request.getParameterMap());
            return new Forward(page, true);
        }
    }
}
