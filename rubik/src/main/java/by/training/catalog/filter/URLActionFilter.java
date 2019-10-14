package by.training.catalog.filter;

import by.training.catalog.controller.command.Command;
import by.training.catalog.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class URLActionFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(final ServletRequest servletRequestNew,
                         final ServletResponse servletResponseNew,
                         final FilterChain filterChainNew)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequestNew;
        HttpServletResponse resp = (HttpServletResponse) servletResponseNew;
        String action = getActionFromURI(req);
        Command command = CommandProvider.getCommand(action);
        pageToReturn(req, action);
        if (command == null) {
            resp.sendError(404);
        } else {
            servletRequestNew.setAttribute("command", command);
            filterChainNew.doFilter(req, resp);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    private String getActionFromURI(final HttpServletRequest request) {
        LOGGER.debug("Request method: {}", request.getMethod());
        String context = request.getContextPath();
        String requestURI = request.getRequestURI();
        LOGGER.debug("Request URI: {}", requestURI);
        int contextLength = context.length();
        int endRequestIndex = requestURI.lastIndexOf(".html");
        String action;
        if (endRequestIndex != -1) {
            action = requestURI.substring(contextLength, endRequestIndex);
        } else {
            action = "/index";
        }
        LOGGER.debug("Requested action: {}", action);
        return action;
    }

    private void pageToReturn(final HttpServletRequest request,
                              final String action) {
        if (request.getMethod().equals("GET")) {
            if (request.getQueryString() != null) {
                String redirect = action.substring(1) + ".html?"
                        + request.getQueryString();
                request.setAttribute("from", redirect);
            } else {
                request.setAttribute("from", action.substring(1) + ".html");
            }
        }
    }
}
