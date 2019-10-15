package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockedUserCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        int id;
        try {
            id = Integer.parseInt(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            Forward forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 404);
            return forward;
        }
        UserService service = getFactory().createUserService();
        try {
            User user = service.findById(id);
            if (user.isBlocked()){
                user.setBlocked(false);
            } else {
                user.setBlocked(true);
            }
            service.updateState(user);
        } catch (ServiceException eNew) {
            Forward forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
        return new Forward("users.html", true);
    }
}
