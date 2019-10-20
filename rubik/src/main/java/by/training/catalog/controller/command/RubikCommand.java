package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RubikCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id;
        Forward forward;
        try {
            id = Long.parseLong(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(404);
        }
        RubikService rubikService = getFactory().createRubikService();
        try {
            RubiksCube cube = rubikService.findById(id);
            StoreImageService storeImageService =
                    getFactory().createStoreImageService();
            storeImageService.findImagesByRubik(cube);
            requestNew.setAttribute("cube", cube);
            forward = new Forward("WEB-INF/jsp/rubik.jsp");
            return forward;
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
    }
}
