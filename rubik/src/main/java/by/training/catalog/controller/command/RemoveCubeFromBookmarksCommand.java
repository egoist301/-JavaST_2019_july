package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveCubeFromBookmarksCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(404);
        }
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute("user");
        UserService service = getFactory().createUserService();
        try {
            service.removeFromBookmarks(user, id);
            return new Forward("bookmarks.html");
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
    }
}
