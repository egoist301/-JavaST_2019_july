package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.properties.ProjectProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * SAXBuilder.
 */
public class FlowerSAXBuilder implements AbstractBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Set flowers.
     */
    private Set<Flower> flowers = new LinkedHashSet<>();
    /**
     * SAXHandler.
     */
    private FlowerSAXHandler flowerSaxHandler;
    /**
     * XML reader.
     */
    private XMLReader reader;

    /**
     * Default constructor.
     */
    public FlowerSAXBuilder() {
        flowerSaxHandler = new FlowerSAXHandler();
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            String constant = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(constant);
            Schema schema = schemaFactory.newSchema(
                    new File(ProjectProperties.getSchemaFile()));
            parserFactory.setNamespaceAware(true);
            parserFactory.setValidating(false);
            parserFactory.setSchema(schema);
            reader = parserFactory.newSAXParser()
                    .getXMLReader();
            reader.setContentHandler(flowerSaxHandler);
        } catch (SAXException | ParserConfigurationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException();
        }
    }

    /**
     * Getter.
     *
     * @return set flowers.
     */
    @Override
    public Set<Flower> getFlowers() {
        return flowers;
    }

    /**
     * Build set flowers.
     *
     * @param fileName fileName.
     */
    @Override
    public void buildSetFlowers(final String fileName) throws ParserException {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException eNew) {
            throw new ParserException();
        }
        flowers = flowerSaxHandler.getFlowers();
    }
}
