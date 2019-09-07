package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.ArtificialFlower;
import by.training.greenhouse.bean.Color;
import by.training.greenhouse.bean.Flower;
import by.training.greenhouse.bean.FlowerNameTag;
import by.training.greenhouse.bean.LivingFlower;
import by.training.greenhouse.bean.Multiplying;
import by.training.greenhouse.bean.Soil;
import by.training.greenhouse.bean.UnknownTypeException;
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
public class FlowerDOMBuilder implements AbstractBuilder {
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
    @Override
    public Set<Flower> getFlowers() {
        return flowers;
    }

    /**
     * Build set flowers.
     *
     * @param filePath filePath.
     */
    @Override
    public void buildSetFlowers(final String filePath) throws ParserException {
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
                flower = (LivingFlower) buildFlower(element,
                        FlowerNameTag.LIVING_FLOWER);
                flowers.add(flower);
            }
            int artificialLen = artificial.getLength();
            for (int i = 0; i < artificialLen; ++i) {
                Element element = (Element) artificial.item(i);
                ArtificialFlower flower = null;
                flower = (ArtificialFlower)
                        buildFlower(element,
                                FlowerNameTag.ARTIFICIAL_FLOWER);
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
            try {
                ((LivingFlower) flower).setSoil(Soil.fromValue(
                        getElementTextContent(element,
                                FlowerNameTag.SOIL.getValue())));
            } catch (UnknownTypeException eNew) {
                throw new ParserException(eNew);
            }
            ((LivingFlower) flower).setWatering(Integer.parseInt(
                    getElementTextContent(element,
                            FlowerNameTag.WATERING.getValue())));
            try {
                ((LivingFlower) flower).setMultiplying(Multiplying.fromValue(
                        getElementTextContent(element,
                                FlowerNameTag.MULTIPLYING.getValue())));
            } catch (UnknownTypeException eNew) {
                throw new ParserException(eNew);
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
            flower.setStemColor(
                    Color.fromValue(getElementTextContent(element,
                            FlowerNameTag.STEM_COLOR.getValue())));
            flower.setHeight(Integer.parseInt(getElementTextContent(element,
                    FlowerNameTag.HEIGHT.getValue())));
            flower.setTemperature(getElementTextContent(element,
                    FlowerNameTag.TEMPERATURE.getValue()));
            flower.setDiscoveryDate(DateParser.parseDate(
                    getElementTextContent(element,
                            FlowerNameTag.DISCOVERY_DATE.getValue())));
        } catch (IllegalArgumentException | UnknownTypeException eNew) {
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
