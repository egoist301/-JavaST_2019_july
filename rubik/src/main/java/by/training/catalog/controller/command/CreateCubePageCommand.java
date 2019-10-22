package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateCubePageCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        RubikService service = getFactory().createRubikService();
        try {
            List<String> forms = service.readAllForm();
            List<String> manufacturers = service.readAllManufacturer();
            List<String> colors = service.readAllPlasticColor();
            requestNew.setAttribute("forms", forms);
            requestNew.setAttribute("manufacturers", manufacturers);
            requestNew.setAttribute("colors", colors);
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(SERVER_ERROR);
        }
        return new Forward("WEB-INF/jsp/createcube.jsp");
    }
}
