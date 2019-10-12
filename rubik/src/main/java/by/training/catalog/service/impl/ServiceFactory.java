package by.training.catalog.service.impl;

import by.training.catalog.service.RubikService;
import by.training.catalog.service.StoreImageService;
import by.training.catalog.service.UserService;

public class ServiceFactory {
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    public RubikService createRubikService() {
        return new RubikServiceImpl();
    }

    public StoreImageService createStoreImageService() {
        return new StoreImageServiceImpl();
    }
}
