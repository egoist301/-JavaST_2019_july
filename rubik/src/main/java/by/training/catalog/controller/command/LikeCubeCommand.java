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

public class LikeCubeCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        UserService service = getFactory().createUserService();
        CommandResult commandResult;
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter(ID));
        } catch (NumberFormatException eNew) {
            return sendError(NOT_FOUND);
        }
        try {
            HttpSession session = requestNew.getSession(false);
            User user = (User) session.getAttribute(ATTRIBUTE_USER);
            if (service.addCubeToBookmarks(user, id)) {
                LOGGER.debug("added cube");
                commandResult = new CommandResult(RUBIK + id);
                return commandResult;
            } else {
                session.setAttribute(ERROR, BOOKMARKS_MESSAGE);
                commandResult = new CommandResult(RUBIK + id);
                return commandResult;
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
    }
}
