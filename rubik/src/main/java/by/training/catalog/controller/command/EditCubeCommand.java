package by.training.catalog.controller.command;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.RubikService;
import by.training.catalog.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class EditCubeCommand extends AdminCommand {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        long id = Long.parseLong(requestNew.getParameter("id"));

        try {
            List<RawData> rawData = new ArrayList<>();
            List<Part> parts = new ArrayList<>(requestNew.getParts());
            for (Part part : parts) {
                if (part.getSize() != 0) {
                    RawData rd = new RawData();
                    rd.setStream(part.getInputStream());
                    rd.setContentType(part.getContentType());
                    rawData.add(rd);
                }
            }
            RubikService service = getFactory().createRubikService();
            RubiksCube rubiksCube = new RubiksCube(id);
            service.update(rubiksCube, rawData);

        } catch (ServletException | ServiceException eNew) {
            eNew.printStackTrace();
        }
        return null;
    }
}
