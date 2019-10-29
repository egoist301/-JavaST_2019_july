package by.training.catalog.filter;

import by.training.catalog.controller.command.Command;
import by.training.catalog.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * URL action filter.
 */
public class URLActionFilter implements Filter {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Do filter.
     *
     * @param servletRequestNew  request.
     * @param servletResponseNew response.
     * @param filterChainNew     chain.
     * @throws IOException      i/o exception.
     * @throws ServletException servlet exception.
     */
    @Override
    public void doFilter(final ServletRequest servletRequestNew,
                         final ServletResponse servletResponseNew,
                         final FilterChain filterChainNew)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequestNew;
        HttpServletResponse resp = (HttpServletResponse) servletResponseNew;
        String action = getActionFromURI(req);
        Command command = CommandProvider.getCommand(action);
        getPageToReturn(req, action);
        if (command == null) {
            resp.sendError(400);
        } else {
            servletRequestNew.setAttribute("command", command);
            filterChainNew.doFilter(req, resp);
        }
    }

    /**
     * Initialize.
     *
     * @param filterConfig filter config.
     */
    @Override
    public void init(final FilterConfig filterConfig) {

    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {

    }

    /**
     * Getter action from uri.
     *
     * @param request request.
     * @return action.
     */
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

    /**
     * Getter for page to return.
     *
     * @param request request.
     * @param action  action.
     */
    private void getPageToReturn(final HttpServletRequest request,
                                 final String action) {
        if (request.getMethod().equals("GET")) {
            if (request.getQueryString() != null) {
                String redirect = action.substring(1) + ".html?"
                        + request.getQueryString();
                String query = redirect;
                if (redirect.contains("&page")) {
                    String[] querys = redirect.split("&page=\\d");
                    query = querys[0];
                } else if (redirect.contains("page")) {
                    String[] querys = redirect.split("page=\\d");
                    query = querys[0];
                }
                request.setAttribute("query", query);
                LOGGER.debug("QUERY!!!!!!!!  {}", request.getParameterMap());
                LOGGER.debug("REDIRECT!!!!!!  {}", redirect);
                redirect = redirect.replace("&", "%26");
                request.setAttribute("from", redirect);
            } else {
                request.setAttribute("from", action.substring(1) + ".html");
            }
        }
    }
}
