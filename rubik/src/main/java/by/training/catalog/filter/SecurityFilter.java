package by.training.catalog.filter;

import by.training.catalog.bean.Role;
import by.training.catalog.bean.User;
import by.training.catalog.controller.command.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Command command = (Command) req.getAttribute("command");
        HttpSession session = req.getSession(true);
        Set<Role> commandRoles = command.getRoles();
        User user = (User) session.getAttribute("user");
        if (commandRoles.isEmpty()) {
            if (user != null && (command instanceof LoginPageCommand
                    || command instanceof SignInCommand
                    || command instanceof RegistrationCommand
                    || command instanceof RegistrationPageCommand)) {
                resp.sendRedirect("index.html");
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            boolean canExecute =
                    user != null && commandRoles.contains(user.getRole());
            if (canExecute) {
                if (!user.isBlocked() || command instanceof SignOutCommand) {
                    chain.doFilter(req, resp);
                } else {
                    resp.sendError(404);
                }
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
    public void init(final FilterConfig config) {

    }

}
