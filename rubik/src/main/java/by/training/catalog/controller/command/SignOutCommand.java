package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand extends Command {

    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew)
            throws IOException {
        HttpSession session = requestNew.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forward("index.html", true);
    }
}