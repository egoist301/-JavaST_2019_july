package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.Flower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * SAXBuilder.
 */
public class SAXBuilder implements AbstractBuilder {
    /**
     * Set flowers.
     */
    private Set<Flower> flowers = new LinkedHashSet<>();
    /**
     * SAXHandler.
     */
    private SAXHandler saxHandler;
    /**
     * XML reader.
     */
    private XMLReader reader;

    /**
     * Default constructor.
     */
    public SAXBuilder() {
        saxHandler = new SAXHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(saxHandler);
        } catch (SAXException eNew) {
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
    public void buildSetFlowers(final String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException eNew) {
            eNew.printStackTrace();
        }
        flowers = saxHandler.getFlowers();
    }
}
