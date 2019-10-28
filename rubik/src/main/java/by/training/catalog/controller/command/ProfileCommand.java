package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCommand extends UserCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        final String profile = "profile.html";
        UserService userService = getFactory().createUserService();
        String oldPassword = requestNew.getParameter("passwordOld");
        String newPassword = requestNew.getParameter("password");
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute("user");
        LOGGER.debug(user);
        Forward forward;
        try {
            if (userService.authorize(user.getUsername(),
                    oldPassword) != null) {
                user.setPassword(newPassword);
                LOGGER.debug(user);
                userService.update(user);
                forward = new Forward(profile, true);
                LOGGER.debug("password is change");
            } else {
                session.setAttribute("error", "profile.incorrect");
                forward = new Forward(profile);
                LOGGER.debug("incorrect old password");
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return forward;
    }
}
