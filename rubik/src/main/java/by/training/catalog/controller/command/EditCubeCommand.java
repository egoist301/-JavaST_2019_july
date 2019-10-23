package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class EditCubeCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id = Long.parseLong(requestNew.getParameter("id"));
        List<String> parameters = new ArrayList<>();
        parameters.add(requestNew.getParameter("model"));
        parameters.add(requestNew.getParameter("price"));
        parameters.add(requestNew.getParameter("weight"));
        parameters.add(requestNew.getParameter("info"));
        parameters.add(requestNew.getParameter("size"));
        parameters.add(requestNew.getParameter("manufacturer"));
        parameters.add(requestNew.getParameter("form"));
        parameters.add(requestNew.getParameter("plasticColor"));
        parameters.add(requestNew.getParameter("primaryPlastic"));
        try {

            RubikService service = getFactory().createRubikService();
            service.update(id, parameters);
        } catch (ServiceException eNew) {
            return sendError(SERVER_ERROR);
        }
        return new Forward("editcube.html?id=" + id);
    }
}
