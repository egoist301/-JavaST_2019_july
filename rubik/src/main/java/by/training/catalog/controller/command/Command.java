package by.training.catalog.controller.command;

import by.training.catalog.bean.Role;
import by.training.catalog.controller.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Command {
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    protected Forward sendError(final int error) {
        Forward forward = new Forward();
        forward.setError(true);
        forward.getAttributes().put("error", error);
        return forward;
    }

    public abstract Forward execute(HttpServletRequest requestNew,
                                    HttpServletResponse responseNew)
            throws IOException;

    public static class Forward {
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

        public Forward() {
        }

        public Forward(final String urlNew) {
            url = urlNew;
            attributes = new HashMap<>();
        }

        public Forward(final String urlNew, final boolean redirectNew) {
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
