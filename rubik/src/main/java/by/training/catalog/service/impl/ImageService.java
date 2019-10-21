package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.dao.PersistentException;
import by.training.catalog.dao.impl.ImageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ImageService {
    private Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIRECTORY = "img/";

    public String save(final RawData rawData) {
        if (rawData == null) {
            return null;
        }
        String permittedContentType = "image/";
        String contentType = rawData.getContentType();
        if (!contentType.contains(permittedContentType)) {
            logger.warn("Attempt to load file with prohibited content type");
            return null;
        }
        String container = contentType.substring(permittedContentType.length());
        try {
            byte[] data = rawData.getStream().readAllBytes();
            logger.debug("Read {} bytes of data.", data.length);
            return ImageManager.save(data, UPLOAD_DIRECTORY, container);
        } catch (IOException e) {
            logger.error("Cannot save image. {}", e.getMessage());
            return null;
        }
    }

    public boolean delete(final String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            logger.debug("Cannot delete file, path is null.");
            return false;
        }
        logger.debug("Attempt to delete file: {}", relativePath);
        try {
            ImageManager.delete(relativePath);
        } catch (PersistentException e) {
            logger.error("Cannot delete file. {}", e.getMessage());
            return false;
        }
        return true;
    }
}
