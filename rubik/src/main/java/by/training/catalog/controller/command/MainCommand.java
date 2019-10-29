package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.INDEX_JSP;

public class MainCommand extends Command {
    @Override
    public Forward execute(final HttpServletRequest requestNew,
                           final HttpServletResponse responseNew) {
        return new Forward(INDEX_JSP);
    }
}
