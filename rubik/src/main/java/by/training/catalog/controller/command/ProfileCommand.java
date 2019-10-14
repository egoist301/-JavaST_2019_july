package by.training.catalog.controller.command;

import by.training.catalog.bean.User;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;
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
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        String oldPassword = requestNew.getParameter("passwordOld");
        String newPassword = requestNew.getParameter("password");
        HttpSession session = requestNew.getSession(false);
        User user = (User) session.getAttribute("user");
        LOGGER.debug(user);
        boolean correctPassword = false;
        Forward forward = null;
        try {
            if (userService.findAccountByLoginAndPassword(user.getUsername(),
                    oldPassword) != null) {
                correctPassword = true;
            } else {
                forward = new Forward(profile);
                forward.getAttributes().put("error", "error.message"
                        + ".invalidPassword");
                LOGGER.debug("incorrect old password");
            }
        } catch (ServiceException eNew) {
            forward = new Forward(profile);
            forward.getAttributes().put("error", "error.message"
                    + ".invalidPassword");
            LOGGER.debug("incorrect old password");
        }
        LOGGER.debug(correctPassword);
        if (correctPassword) {
            user.setPassword(newPassword);
            LOGGER.debug(user);
            try {
                userService.update(user);
                forward = new Forward(profile, true);
                LOGGER.debug("password is change");
            } catch (ServiceException eNew) {
                //TODO Logger
            }
        }
        return forward;
    }
}
