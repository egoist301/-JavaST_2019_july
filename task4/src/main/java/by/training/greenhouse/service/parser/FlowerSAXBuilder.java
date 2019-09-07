package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
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
