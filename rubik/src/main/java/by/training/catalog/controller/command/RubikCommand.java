package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.*;

public class RubikCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id;
        Forward forward;
        try {
            id = Long.parseLong(requestNew.getParameter(ID));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(NOT_FOUND);
        }
        RubikService rubikService = getFactory().createRubikService();
        try {
            RubiksCube cube = rubikService.findById(id);
            if (cube != null) {
                StoreImageService storeImageService =
                        getFactory().createStoreImageService();
                storeImageService.assignRubikImagesPaths(cube);
                requestNew.setAttribute(CUBE, cube);
                forward = new Forward(RUBIK_JSP);
                return forward;
            }
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return sendError(NOT_FOUND);
    }
}
