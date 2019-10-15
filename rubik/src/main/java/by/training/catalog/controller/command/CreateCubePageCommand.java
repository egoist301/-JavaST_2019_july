package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.impl.ServiceFactory;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCubePageCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        ServiceFactory factory = new ServiceFactory();
        RubikService service = factory.createRubikService();
        try {
            List<String> forms = service.readAllForm();
            List<String> manufacturers = service.readAllManufacturer();
            List<String> colors = service.readAllPlasticColor();
            requestNew.setAttribute("forms", forms);
            requestNew.setAttribute("manufacturers", manufacturers);
            requestNew.setAttribute("colors", colors);
        } catch (ServiceException eNew) {
            Forward forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
        return new Forward("WEB-INF/jsp/createcube.jsp");
    }
}
