package by.training.composite.controller;

import by.training.composite.bean.Component;
import by.training.composite.service.FactoryTree;

/**
 * Load text from file.
 */
public class LoadTextFromFile {
    /**
     * Load component.
     *
     * @param filePath filePath.
     * @return component.
     */
    public Component load(final String filePath) {
        return new FactoryTree().createText(filePath);
    }
}
