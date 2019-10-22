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

public class LikeCubeCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        UserService service = getFactory().createUserService();
        Forward forward;
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            return sendError(NOT_FOUND);
        }
        try {
            HttpSession session = requestNew.getSession(false);
            User user = (User) session.getAttribute("user");
            if (service.addCubeToBookmarks(user, id)) {
                LOGGER.debug("added cube");
                forward = new Forward("rubik.html?id=" + id);
                return forward;
            } else {
                forward = new Forward("rubik.html?id=" + id);
                return forward;
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
    }
}
