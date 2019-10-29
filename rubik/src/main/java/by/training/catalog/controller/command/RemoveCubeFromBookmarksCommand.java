package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.training.catalog.constant.ApplicationConstants.*;

public class RemoveCubeFromBookmarksCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter(ID));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(NOT_FOUND);
        }
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        UserService service = getFactory().createUserService();
        try {
            service.removeFromBookmarks(user, id);
            return new CommandResult(BOOKMARKS);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
    }
}
