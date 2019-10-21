package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//TODO fix
public class EditCubePageCommand extends AdminCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        Forward forward;
        long id;
        try {
            id = Long.parseLong(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            LOGGER.error(eNew);
            return sendError(404);
        }
        RubikService service = getFactory().createRubikService();
        try {
            List<String> forms = service.readAllForm();
            RubiksCube cube = service.findById(id);
            List<String> manufacturers = service.readAllManufacturer();
            List<String> colors = service.readAllPlasticColor();
            requestNew.setAttribute("forms", forms);
            requestNew.setAttribute("manufacturers", manufacturers);
            requestNew.setAttribute("colors", colors);
            requestNew.setAttribute("cube", cube);
            forward = new Forward("WEB-INF/jsp/editrubik.jsp");
            return forward;
        } catch (ServiceException eNew) {
            LOGGER.error(eNew);
            return sendError(500);
        }
    }
}
