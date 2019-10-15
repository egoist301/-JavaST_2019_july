package by.training.catalog.controller.command;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RubikCommand extends Command {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        int id;
        Forward forward;
        try {
            id = Integer.parseInt(requestNew.getParameter("id"));
        } catch (NumberFormatException eNew) {
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 404);
            return forward;
        }
        ServiceFactory factory = new ServiceFactory();
        RubikService rubikService = factory.createRubikService();
        try {
            RubiksCube cube = rubikService.findById(id);
            requestNew.setAttribute("cube", cube);
            forward = new Forward("WEB-INF/jsp/rubik.jsp");
            return forward;
        } catch (ServiceException eNew) {
            forward = new Forward();
            forward.setError(true);
            forward.getAttributes().put("error", 500);
            return forward;
        }
    }
}
