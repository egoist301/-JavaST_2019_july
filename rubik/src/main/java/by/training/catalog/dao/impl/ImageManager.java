package by.training.catalog.dao.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.service.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Image manager. Save and delete file.
 */
public final class ImageManager {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Filename length.
     */
    private static final int FILENAME_LENGTH = 15;

    /**
     * Default constructor.
     */
    private ImageManager() {
    }

    /**
     * Save file.
     *
     * @param bytes     bytes.
     * @param folder    name of folder.
     * @param container container.
     * @return path.
     */
    public static String save(final byte[] bytes,
                              final String folder,
                              final String container) {
        try {
            File file;
            String filename;
            String path;
            do {
                filename = RandomStringGenerator.generate(FILENAME_LENGTH);
                path = String.format("%s%s/%s.%s", RawData.getRootPath(),
                        folder, filename, container);
                file = new File(path);
            } while (file.exists());
            if (!file.createNewFile()) {
                LOGGER.debug("Cannot create new file.");
                return null;
            }
            Files.write(Paths.get(path), bytes);
            return String.format("%s/%s.%s", folder, filename, container);
        } catch (IOException e) {
            LOGGER.error("Cannot write data. {}", e.getMessage());
            return null;
        }
    }
}
