package by.training.catalog.service.impl;

import by.training.catalog.bean.RawData;
import by.training.catalog.dao.impl.ImageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ImageService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIRECTORY = "img";
    private static final String PERMITTED_CONTENT_TYPE = "image/";

    public String save(final RawData rawData) {
        if (rawData == null) {
            return null;
        }
        String contentType = rawData.getContentType();
        if (!contentType.contains(PERMITTED_CONTENT_TYPE)) {
            LOGGER.warn("Attempt to load file with prohibited content type");
            return null;
        }
        String container = contentType.substring(PERMITTED_CONTENT_TYPE.length());
        try {
            byte[] data = rawData.getStream().readAllBytes();
            LOGGER.debug("Read {} bytes of data.", data.length);
            String temp = ImageManager.save(data, UPLOAD_DIRECTORY, container);
            LOGGER.debug("TEMP!!!!!!   {}", temp);
            return temp;
        } catch (IOException e) {
            LOGGER.error("Cannot save image. {}", e.getMessage());
            return null;
        }
    }
}
