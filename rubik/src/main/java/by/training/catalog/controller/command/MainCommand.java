package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainCommand extends Command {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        return new Forward("WEB-INF/jsp/index.jsp");
    }
}
