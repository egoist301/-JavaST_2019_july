package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.StoreImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RubikCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        int id;
        Forward forward;
        try {
            id = Integer.parseInt(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 404);
            return forward;
        }
        RubikService rubikService = getFactory().createRubikService();
        try {
            RubiksCube cube = rubikService.findById(id);
            StoreImageService storeImageService =
                    getFactory().createStoreImageService();
            List<String> list = storeImageService.findImagesByRubik(cube);
            requestNew.setAttribute("paths", list);
            requestNew.setAttribute("cube", cube);
            forward = new Forward("WEB-INF/jsp/rubik.jsp");
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
