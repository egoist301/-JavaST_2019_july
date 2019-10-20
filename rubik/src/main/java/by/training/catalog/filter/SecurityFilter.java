package by.training.catalog.filter;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;
import by.training.catalog.controller.command.Command;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Security filter. Restricts users access.
 */
public class SecurityFilter implements Filter {
    /**
     * Destroy.
     */
    @Override
    public void destroy() {
    }

    /**
     * Do filter.
     *
     * @param request  request.
     * @param response response.
     * @param chain    chain.
     * @throws ServletException servlet exception.
     * @throws IOException      i/o exception.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Command command = (Command) req.getAttribute("command");
        HttpSession session = req.getSession(true);
        Set<Role> commandRoles = command.getRoles();
        if (commandRoles.isEmpty()) {
            chain.doFilter(req, resp);
        } else {
            User user = (User) session.getAttribute("user");
            boolean canExecute =
                    user != null && commandRoles.contains(user.getRole());
            if (canExecute) {
                chain.doFilter(req, response);
            } else {
                resp.sendError(404);
            }
        }
    }

    /**
     * Initialize.
     *
     * @param config filter config.
     */
    @Override
    public void init(FilterConfig config) {

    }

}
