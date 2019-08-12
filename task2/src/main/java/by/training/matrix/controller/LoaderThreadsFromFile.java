package by.training.matrix.controller;

import by.training.matrix.service.ThreadService;

/**
 * Loader threads from file.
 */
public class LoaderThreadsFromFile {
    /**
     * Load data from file.
     *
     * @param filePath filepath.
     * @return number-element.
     */
    public int[] load(final String filePath) {
        return ThreadService.getThreadService().createUniqueNumber(filePath);
    }
}
