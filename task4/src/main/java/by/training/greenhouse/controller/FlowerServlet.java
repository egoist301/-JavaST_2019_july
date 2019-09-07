package by.training.greenhouse.controller;

import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.service.factory.FlowerFactory;
import by.training.greenhouse.service.parser.AbstractBuilder;
import by.training.greenhouse.service.parser.ParserException;
import by.training.greenhouse.service.validator.ValidatorXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Runner.
 */
@WebServlet("/hello")
public class FlowerServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * XSD schema.
     */
    private static final String INPUT_XSD = "data//greenhouse.xsd";
    /**
     * Result.
     */
    private static final String RESULT = "jsp/result.jsp";

    /**
     * Do get.
     *
     * @param request  request.
     * @param response response.
     * @throws IOException      input\output exception.
     * @throws ServletException servlet exception.
     */
    @Override
    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Do post.
     *
     * @param req  request.
     * @param resp response.
     * @throws ServletException servlet exception.
     * @throws IOException      input\output exception.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    /**
     * Request processing.
     *
     * @param request  -request.
     * @param response -response.
     * @throws ServletException -some servlet exception.
     * @throws IOException      -I/O exception.
     */
    private void processRequest(final HttpServletRequest request,
                                final HttpServletResponse response)
            throws ServletException, IOException {
        String parserType = request.getParameter("parser");
        String xmlPath = request.getParameter("file");
        AbstractBuilder parser =
                FlowerFactory.getINSTANCE().createFlowerBuilder(parserType);
        if (ValidatorXML.validate(xmlPath,
                getServletContext().getResource(INPUT_XSD).getPath())) {
            try {
                parser.buildSetFlowers(xmlPath);
            } catch (ParserException eNew) {
                LOGGER.error(eNew);
                throw new RuntimeException();
            }
        }
        Set<Flower> flowers = parser.getFlowers();
        request.setAttribute("flowers", flowers);
        request.getRequestDispatcher(RESULT)
                .forward(request, response);
    }

}
