package by.training.catalog.service.impl;

import by.training.catalog.service.UserService;

public class ServiceFactory {
    public UserService createUserService() {
        return new UserServiceImpl();
    }
}
