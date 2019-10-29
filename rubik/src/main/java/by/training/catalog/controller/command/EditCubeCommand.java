package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static by.training.catalog.constant.ApplicationConstants.*;

public class EditCubeCommand extends AdminCommand {

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        long id = Long.parseLong(requestNew.getParameter(ID));
        List<String> parameters = new ArrayList<>();
        HttpSession session = requestNew.getSession(false);
        AddRubikCommand.fillWithParameters(requestNew, parameters);
        try {
            RubikService service = getFactory().createRubikService();
            service.update(id, parameters);
        } catch (ServiceException eNew) {
            session.setAttribute(ERROR, RUBIK_MESSAGE);
        }
        return new Forward(EDIT_CUBE + id);
    }
}
