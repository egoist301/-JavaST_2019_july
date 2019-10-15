package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockedCubeCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
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
        RubikService service = getFactory().createRubikService();
        try {
            RubiksCube cube = service.findById(id);
            if (cube.isBlocked()) {
                cube.setBlocked(false);
            } else {
                cube.setBlocked(true);
            }
            service.update(cube);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            Forward forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
        return new Forward("catalog.html", true);
    }
}
