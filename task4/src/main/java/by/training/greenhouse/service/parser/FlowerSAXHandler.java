package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.ArtificialFlower;
import by.training.greenhouse.bean.Color;
import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.bean.FlowerNameTag;
import by.training.greenhouse.bean.LivingFlower;
import by.training.greenhouse.bean.Multiplying;
import by.training.greenhouse.bean.Soil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * SAX handler.
 */
public class FlowerSAXHandler extends DefaultHandler {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Set flowers.
     */
    private final Set<Flower> flowers = new LinkedHashSet<>();
    /**
     * Name element.
     */
    private String thisElement;
    /**
     * Flower.
     */
    private Flower flower;

    /**
     * Start parse document.
     */
    @Override
    public void startDocument() {
        LOGGER.info("Start parse XML");
    }

    /**
     * End parse document.
     */
    @Override
    public void endDocument() {
        LOGGER.info("End parse XML");
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
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName,
                             final Attributes attributes) {
        thisElement = qName;
        if (thisElement != null) {
            if (thisElement.equals(FlowerNameTag.LIVING_FLOWER.getValue())) {
                flower = new LivingFlower();
                flower.setId(Integer.parseInt(attributes.getValue(
                        FlowerNameTag.ID.getValue())));
                ((LivingFlower) flower).setMedicinal(Boolean.
                        parseBoolean(attributes.getValue(FlowerNameTag
                                .MEDICINAL.getValue())));
                ((LivingFlower) flower).setPhotophilous(Boolean.
                        parseBoolean(attributes.getValue(FlowerNameTag
                                .PHOTOPHILOUS.getValue())));
            } else if (thisElement.equals(FlowerNameTag
                    .ARTIFICIAL_FLOWER.getValue())) {
                flower = new ArtificialFlower();
                flower.setId(Integer.parseInt(attributes.getValue(
                        FlowerNameTag.ID.getValue())));
            }
        } else {
            LOGGER.warn("Empty element.");
        }
    }

    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        thisElement = null;
        if (qName.equals(FlowerNameTag.ARTIFICIAL_FLOWER.getValue())
                || qName.equals(FlowerNameTag.LIVING_FLOWER.getValue())) {
            flowers.add(flower);
        }
    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(final char[] ch, final int start,
                           final int length) {
        if (thisElement != null) {
            String string = new String(ch, start, length);
            if (thisElement.equals(FlowerNameTag.NAME.getValue())) {
                flower.setName(string);
            }
            if (thisElement.equals(FlowerNameTag.ORIGIN.getValue())) {
                flower.setOrigin(string);
            }
            if (thisElement.equals(FlowerNameTag.STEM_COLOR.getValue())) {
                flower.setStemColor(Color.fromValue(string));
            }
            if (thisElement.equals(FlowerNameTag.HEIGHT.getValue())) {
                flower.setHeight(Integer.parseInt(string));
            }
            if (thisElement.equals(FlowerNameTag.MATERIAL.getValue())) {
                ((ArtificialFlower) flower).setMaterial(string);
            }
            if (thisElement.equals(FlowerNameTag.MULTIPLYING.getValue())) {
                ((LivingFlower) flower).setMultiplying(Multiplying
                        .fromValue(string));
            }
            if (thisElement.equals(FlowerNameTag.SOIL.getValue())) {
                ((LivingFlower) flower).setSoil(Soil.fromValue(string));
            }
            if (thisElement.equals(FlowerNameTag.TEMPERATURE.getValue())) {
                flower.setTemperature(string);
            }
            if (thisElement.equals(FlowerNameTag.WATERING.getValue())) {
                ((LivingFlower) flower).setWatering(Integer.parseInt(string));
            }
            if (thisElement.equals(FlowerNameTag.DISCOVERY_DATE.getValue())) {
                flower.setDiscoveryDate(DateParser.parseDate(string));
            }
        }
    }

    /**
     * Getter.
     *
     * @return set flowers.
     */
    public Set<Flower> getFlowers() {
        return flowers;
    }
}
