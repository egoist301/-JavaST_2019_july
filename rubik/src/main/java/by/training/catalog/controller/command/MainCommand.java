package by.training.catalog.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.training.catalog.constant.ApplicationConstants.INDEX_JSP;

public class MainCommand extends Command {
    @Override
    public CommandResult execute(final HttpServletRequest requestNew,
                                 final HttpServletResponse responseNew) {
        return new CommandResult(INDEX_JSP);
    }
}
