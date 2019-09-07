package by.training.greenhouse.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project properties.
 */
public final class ProjectProperties {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Input XML file.
     */
    private static String inputFile;
    /**
     * XSD schema file.
     */
    private static String schemaFile;
    /**
     * Parser type.
     */
    private static String parserType;

    /**
     * Default constructor.
     */
    private ProjectProperties() {
    }

    /**
     * Load properties.
     *
     * @param fileName file name.
     */
    public static void loadProperties(final String fileName) {

        ClassLoader classLoader = ProjectProperties.class.getClassLoader();
        Properties property = new Properties();
        try (InputStream inputStream = classLoader
                .getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException();
            }
            property.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        inputFile = property.getProperty("filename.input");
        schemaFile = property.getProperty("filename.schema");
        parserType = property.getProperty("parser");
    }

    /**
     * Getter.
     *
     * @return input xml file.
     */
    public static String getInputFile() {
        return inputFile;
    }

    /**
     * Getter.
     *
     * @return xsd schema.
     */
    public static String getSchemaFile() {
        return schemaFile;
    }

    /**
     * Getter.
     *
     * @return parser type.
     */
    public static String getParserType() {
        return parserType;
    }
}
