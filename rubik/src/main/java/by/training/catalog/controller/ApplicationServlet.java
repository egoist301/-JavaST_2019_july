package by.training.catalog.controller;

import by.training.catalog.controller.command.Command;
import by.training.catalog.dao.pool.ConnectionPool;
import by.training.catalog.service.ServiceException;
import by.training.catalog.service.ServiceInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.html")
public class ApplicationServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        ServiceInitializer.init();
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = (Command) req.getAttribute("command");
        Command.Forward forward = command.execute(req, resp);
        try {
            if (!checkError(forward, resp)) {
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

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = (Command) req.getAttribute("command");
        Command.Forward forward = command.execute(req, resp);
        try {
            if (!checkError(forward, resp)) {
                resp.sendRedirect(forward.getUrl());
            }
        } catch (IOException e) {
            LOGGER.error("Cannot redirect user. {}", e.getMessage());
        }
    }

    private boolean checkError(final Command.Forward forward,
                               final HttpServletResponse response) {
        if (forward.isError()) {
            try {
                int error = (Integer) forward.getAttributes().get("error");
                response.sendError(error);
                return true;
            } catch (IOException e) {
                LOGGER.error("Cannot redirect user.");
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().close();
    }
}
