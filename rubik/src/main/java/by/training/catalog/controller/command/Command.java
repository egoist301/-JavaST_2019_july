package by.training.catalog.controller.command;

import by.training.catalog.bean.Role;
import by.training.catalog.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Command {
    static final int NOT_FOUND = 404;
    static final int SERVER_ERROR = 500;
    private ServiceFactory factory = new ServiceFactory();
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public ServiceFactory getFactory() {
        return factory;
    }

    protected CommandResult sendError(final int error) {
        CommandResult commandResult = new CommandResult();
        commandResult.setError(true);
        commandResult.getAttributes().put("error", error);
        return commandResult;
    }

    public abstract CommandResult execute(HttpServletRequest requestNew,
                                          HttpServletResponse responseNew)
            throws IOException;

    public static class CommandResult {
        private String url;
        private Map<String, Object> attributes;
        private boolean redirect;
        private boolean error;

        public boolean isError() {
            return error;
        }

        public void setError(final boolean errorNew) {
            error = errorNew;
        }

        public CommandResult() {
        }

        public CommandResult(final String urlNew) {
            url = urlNew;
            attributes = new HashMap<>();
        }

        public CommandResult(final String urlNew, final boolean redirectNew) {
            url = urlNew;
            attributes = new HashMap<>();
            redirect = redirectNew;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(final boolean redirectNew) {
            redirect = redirectNew;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String urlNew) {
            url = urlNew;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }

        public void setAttributes(
                final Map<String, Object> attributesNew) {
            attributes = attributesNew;
        }
    }
}
