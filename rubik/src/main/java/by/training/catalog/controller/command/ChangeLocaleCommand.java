package by.training.catalog.controller.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeLocaleCommand extends Command {
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
        //String url = request.getParameter("from");

        Forward forward;
        if (locale != null) {
            Cookie cookie = new Cookie("locale", locale);
            response.addCookie(cookie);
        }
        forward = new Forward("index.html", true);
        return forward;
    }
}
