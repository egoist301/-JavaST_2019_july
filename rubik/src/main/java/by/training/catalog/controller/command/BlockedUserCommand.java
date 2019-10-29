package by.training.catalog.controller.command;

import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.ID;
import static by.training.catalog.constant.ApplicationConstants.USERS_HTML;

public class BlockedUserCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter(ID));
            LOGGER.debug("Id = {}", id);
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(NOT_FOUND);
        }

        UserService service = getFactory().createUserService();
        try {
            service.updateState(id);
        } catch (ServiceException eNew) {
            return sendError(SERVER_ERROR);
        }
        return new Forward(USERS_HTML, true);
    }
}
