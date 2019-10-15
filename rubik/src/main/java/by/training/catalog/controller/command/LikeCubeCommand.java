package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.bean.User;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.UserService;
import by.training.catalog.service.impl.ServiceFactory;
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
        ServiceFactory factory = new ServiceFactory();
        UserService service = factory.createUserService();
        Forward forward;
        int id;
        try {
            id = Integer.parseInt(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 404);
            return forward;
        }
        try {
            RubikService rubikService = factory.createRubikService();
            RubiksCube cube = rubikService.findById(id);
            HttpSession session = requestNew.getSession(false);
            User user = (User) session.getAttribute("user");
            RubiksCube cube1 = service.findCubeFromBasket(user, cube);
            LOGGER.debug("Cube1: {}", cube);
            LOGGER.debug("Cube2: {}", cube1);
            LOGGER.debug("User: {}", user);
            if (cube1 == null) {
                service.addCubeToBasket(user, cube);
                LOGGER.debug("added cube");
            }
            forward = new Forward("rubik.html?id=" + id);
            return forward;
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }

    }
}
