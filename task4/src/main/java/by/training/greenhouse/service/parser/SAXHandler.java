package by.training.greenhouse.service.parser;

import by.training.greenhouse.garbage.Flower;
import by.training.greenhouse.garbage.Greenhouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private Greenhouse greenhouse = new Greenhouse();
    private String thisElement;
    private Flower flower = new Flower();

    @Override
    public void startDocument() {
        LOGGER.info("Start parse XML");
    }

    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#startElement
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName,
                             final Attributes attributes) throws SAXException {
        thisElement = qName;
        if (thisElement != null) {
        }
    }
}
