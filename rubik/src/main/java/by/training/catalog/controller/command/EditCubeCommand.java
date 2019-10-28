package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class EditCubeCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id = Long.parseLong(requestNew.getParameter("id"));
        List<String> parameters = new ArrayList<>();
        HttpSession session = requestNew.getSession(false);
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
            session.setAttribute("error", "rubik.incorrect");
        }
        return new Forward("editcube.html?id=" + id);
    }
}
