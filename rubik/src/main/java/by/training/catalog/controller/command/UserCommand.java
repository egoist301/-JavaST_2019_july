package by.training.catalog.controller.command;

import by.training.catalog.bean.Role;

import java.util.Arrays;

public abstract class UserCommand extends Command {
    public UserCommand() {
        getRoles().addAll(Arrays.asList(Role.values()));
    }
}
