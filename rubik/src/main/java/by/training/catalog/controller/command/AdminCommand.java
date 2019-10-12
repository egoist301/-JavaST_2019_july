package by.training.catalog.controller.command;

import by.training.catalog.bean.Role;

public abstract class AdminCommand extends Command {
    public AdminCommand() {
        getRoles().add(Role.ADMIN);
    }
}
