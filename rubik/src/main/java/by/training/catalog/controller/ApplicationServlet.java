package by.training.catalog.controller;

import by.training.catalog.controller.command.Command;
import by.training.catalog.dao.pool.ConnectionPool;
import by.training.catalog.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Application servlet. Runner.
 */
@WebServlet("*.html")
public class ApplicationServlet extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Initialize project and database.
     */
    @Override
    public void init() {
        ResourceBundle bundle = ResourceBundle.getBundle("database");
        ServiceInitializer.init(bundle);
    }

    /**
     * Get.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) {
        Command command = (Command) req.getAttribute("command");
        try {
            Command.Forward forward = command.execute(req, resp);
            if (checkError(forward, resp)) {
                if (forward.isRedirect()) {
                    resp.sendRedirect(forward.getUrl());
                } else {
                    req.getRequestDispatcher(forward.getUrl())
                            .forward(req, resp);
                }
            }
        } catch (ServletException | IOException e) {
            LOGGER.error("Cannot forward user. {}", e.getMessage());
        }
    }

    /**
     * Post.
     *
     * @param req  request.
     * @param resp response.
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        Command command = (Command) req.getAttribute("command");
        try {
            Command.Forward forward = command.execute(req, resp);
            if (checkError(forward, resp)) {
                resp.sendRedirect(forward.getUrl());
            }
        } catch (IOException e) {
            LOGGER.error("Cannot redirect user. {}", e.getMessage());
        }
    }

    /**
     * Check forward. Contains error or not.
     *
     * @param forward  forward.
     * @param response response.
     * @return true or false.
     */
    private boolean checkError(final Command.Forward forward,
                               final HttpServletResponse response) {
        if (forward.isError()) {
            try {
                int error = (Integer) forward.getAttributes().get("error");
                response.sendError(error);
                return false;
            } catch (IOException e) {
                LOGGER.error("Cannot redirect user.");
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * Close connection pool.
     */
    @Override
    public void destroy() {
        ConnectionPool.getInstance().close();
    }
}
