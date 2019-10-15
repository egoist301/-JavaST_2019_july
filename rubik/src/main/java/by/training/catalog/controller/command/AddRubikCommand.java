package by.training.catalog.controller.command;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRubikCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {

        String model = requestNew.getParameter("model");
        String price = requestNew.getParameter("price");
        String weight = requestNew.getParameter("weight");
        String info = requestNew.getParameter("info");
        String manufacturer = requestNew.getParameter("manufacturer");
        String form = requestNew.getParameter("form");
        String plasticColor = requestNew.getParameter("plasticColor");
        String primaryPlastic = requestNew.getParameter("primaryPlastic");
        String size = requestNew.getParameter("size");
        ServiceFactory factory = new ServiceFactory();
        RubikService service = factory.createRubikService();
        /*try {
            service.create();
        } catch (ServiceException eNew) {

        }*/
        return null;
    }
}
