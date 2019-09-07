package by.training.greenhouse.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Validator XML.
 */
public final class ValidatorXML {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Message.
     */
    private static final String VALID = " is valid";
    /**
     * Message.
     */
    private static final String NOT_VALID = " is not valid";
    /**
     * Message.
     */
    private static final String NULL_POINTER_ERROR
            = "File name or schema name is null";

    /**
     * Default constructor.
     */
    private ValidatorXML() {
    }

    /**
     * Validate xml.
     * @param fileName xml file.
     * @param schemaName xsd file.
     * @return true or false.
     */
    public static boolean validate(final String fileName,
                                   final String schemaName) {
        if (fileName != null && schemaName != null) {
            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(schemaName);

            try {
                Schema schema = factory.newSchema(schemaFile);
                Validator validator = schema.newValidator();
                Source source = new StreamSource(fileName);
                validator.validate(source);
                LOGGER.info(fileName + VALID);
                return true;

            } catch (SAXException e) {
                LOGGER.error(fileName + NOT_VALID + e.getMessage());
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.error(NULL_POINTER_ERROR);
        return false;
    }
}
