package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.ArtificialFlower;
import by.training.greenhouse.bean.Color;
import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.bean.FlowerNameTag;
import by.training.greenhouse.bean.LivingFlower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Flower DOM builder.
 */
public class FlowerDOMBuilder {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Flowers set.
     */
    private Set<Flower> flowers;
    /**
     * Document builder.
     */
    private DocumentBuilder builder;

    /**
     * Default constructor.
     */
    public FlowerDOMBuilder() {
        flowers = new LinkedHashSet<>();
        DocumentBuilderFactory documentBuilderFactory
                = DocumentBuilderFactory.newInstance();
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException eNew) {
            LOGGER.error(eNew);
            throw new RuntimeException();
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

    /**
     * Build set flowers.
     *
     * @param filePath filePath.
     */
    public void buildSetFlowers(final String filePath) {
        Document document;
        try {
            document = builder.parse(filePath);
            Element root = document.getDocumentElement();
            NodeList livings = root.getElementsByTagName(
                    FlowerNameTag.LIVING_FLOWER.getValue());
            NodeList artificial = root.getElementsByTagName(
                    FlowerNameTag.ARTIFICIAL_FLOWER.getValue());
            int livingLen = livings.getLength();
            for (int i = 0; i < livingLen; ++i) {
                Element element = (Element) livings.item(i);
                LivingFlower flower = null;
                try {
                    flower = (LivingFlower) buildFlower(element,
                            FlowerNameTag.LIVING_FLOWER);
                } catch (ParserException eNew) {
                    LOGGER.warn(eNew);
                }
                flowers.add(flower);
            }
            int artificialLen = artificial.getLength();
            for (int i = 0; i < artificialLen; ++i) {
                Element element = (Element) artificial.item(i);
                ArtificialFlower flower = null;
                try {
                    flower = (ArtificialFlower)
                            buildFlower(element,
                                    FlowerNameTag.ARTIFICIAL_FLOWER);
                } catch (ParserException eNew) {
                    LOGGER.warn(eNew);
                }
                flowers.add(flower);
            }
        } catch (SAXException | IOException eNew) {
            LOGGER.error(eNew);
        }
    }

    /**
     * Build flower.
     *
     * @param element element.
     * @param tag     name.
     * @return abstract flower object.
     * @throws ParserException custom exception.
     */
    private Flower buildFlower(final Element element,
                               final FlowerNameTag tag) throws ParserException {
        Flower flower;
        if (tag.equals(FlowerNameTag.ARTIFICIAL_FLOWER)) {
            flower = new ArtificialFlower();
            ((ArtificialFlower) flower).setMaterial(getElementTextContent(
                    element, FlowerNameTag.MATERIAL.getValue()));
        } else if (tag.equals(FlowerNameTag.LIVING_FLOWER)) {
            flower = new LivingFlower();
            if (element.getAttribute(FlowerNameTag
                    .MEDICINAL.getValue()).isEmpty()) {
                ((LivingFlower) flower).setMedicinal(false);
            } else {
                ((LivingFlower) flower).setMedicinal(Boolean.parseBoolean(
                        element.getAttribute(FlowerNameTag
                                .MEDICINAL.getValue())));
            }
            if (element.getAttribute(FlowerNameTag
                    .PHOTOPHILOUS.getValue()).isEmpty()) {
                ((LivingFlower) flower).setPhotophilous(false);
            } else {
                ((LivingFlower) flower).setPhotophilous(Boolean.parseBoolean(
                        element.getAttribute(FlowerNameTag
                                .PHOTOPHILOUS.getValue())));
            }
        } else {
            throw new ParserException();
        }
        try {
            flower.setId(Integer.parseInt(
                    element.getAttribute(FlowerNameTag.ID.getValue())));
        flower.setName(getElementTextContent(element,
                FlowerNameTag.NAME.getValue()));
        flower.setOrigin(getElementTextContent(element,
                FlowerNameTag.ORIGIN.getValue()));
        flower.setStemColor(Color.fromValue(getElementTextContent(element,
                FlowerNameTag.STEM_COLOR.getValue())));
        flower.setHeight(Integer.parseInt(getElementTextContent(element,
                FlowerNameTag.HEIGHT.getValue())));
        flower.setTemperature(getElementTextContent(element,
                FlowerNameTag.TEMPERATURE.getValue()));
        flower.setTemperature(getElementTextContent(element,
                FlowerNameTag.TEMPERATURE.getValue()));
        flower.setDiscoveryDate(DateParser.parseDate(
                getElementTextContent(element,
                        FlowerNameTag.DISCOVERY_DATE.getValue())));
        } catch (IllegalArgumentException eNew) {
            throw new ParserException(eNew);
        }
        return flower;
    }


    /**
     * Getter.
     *
     * @param element     element.
     * @param elementName name.
     * @return string.
     */
    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
