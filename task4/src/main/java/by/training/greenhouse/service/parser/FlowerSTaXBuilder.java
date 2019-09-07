package by.training.greenhouse.service.parser;

import by.training.greenhouse.bean.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Student STaX builder.
 */
public class FlowerSTaXBuilder implements AbstractBuilder {
    /**
     * Set flowers.
     */
    private Set<Flower> flowers = new LinkedHashSet<>();

    /**
     * XML input factory.
     */
    private XMLInputFactory factory;

    /**
     * Default constructor.
     */
    public FlowerSTaXBuilder() {
        factory = XMLInputFactory.newInstance();
    }

    /**
     * Build set flowers.
     *
     * @param fileName fileName.
     */
    @Override
    public void buildSetFlowers(final String fileName) throws ParserException {
        FileInputStream inputStream;
        XMLStreamReader reader;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = factory.createXMLStreamReader(inputStream);
            //StAX paring
            while (reader.hasNext()) {
                Flower flower = null;
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {

                    String name = reader.getLocalName();
                    if (name.equals(FlowerNameTag.LIVING_FLOWER.getValue())) {
                        flower = buildLivingFlower(reader);
                    } else if (name.equals(FlowerNameTag.ARTIFICIAL_FLOWER
                            .getValue())) {
                        flower = buildArtificialFlower(reader);
                    }
                    if (flower != null) {
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new ParserException();
        }
    }

    /**
     * @param reader xml stream reader.
     * @return abstract class object.
     * @throws XMLStreamException exception.
     * @throws ParserException    custom exception.
     */
    private LivingFlower buildLivingFlower(final XMLStreamReader reader)
            throws XMLStreamException, ParserException {
        LivingFlower livingFlower = new LivingFlower();
        /*attributes.*/
        livingFlower.setId(Integer.parseInt(reader.getAttributeValue(null,
                FlowerNameTag.ID.getValue())));
        if ((reader.getAttributeValue(null,
                FlowerNameTag.PHOTOPHILOUS.getValue())) != null) {
            livingFlower.setPhotophilous(Boolean.parseBoolean(
                    reader.getAttributeValue(null,
                            FlowerNameTag.PHOTOPHILOUS.getValue())));
        } else {
            livingFlower.setMedicinal(false);
        }
        if ((reader.getAttributeValue(null,
                FlowerNameTag.MEDICINAL.getValue())) != null) {
            livingFlower.setMedicinal(Boolean.parseBoolean(
                    reader.getAttributeValue(null,
                            FlowerNameTag.MEDICINAL.getValue())));
        } else {
            livingFlower.setMedicinal(false);
        }
        /*content.*/
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerNameTag.fromValue(name)) {
                        case SOIL:
                            try {
                                livingFlower.setSoil(Soil
                                        .fromValue(getXMLText(reader)));
                            } catch (UnknownTypeException eNew) {
                                throw new ParserException(eNew);
                            }
                            break;
                        case NAME:
                            livingFlower.setName(getXMLText(reader));
                            break;
                        case STEM_COLOR:
                            try {
                                livingFlower.setStemColor(Color
                                        .fromValue(getXMLText(reader)));
                            } catch (UnknownTypeException eNew) {
                                throw new ParserException(eNew);
                            }
                            break;
                        case HEIGHT:
                            livingFlower.setHeight(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case TEMPERATURE:
                            livingFlower.setTemperature(getXMLText(reader));
                            break;
                        case WATERING:
                            livingFlower.setWatering(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case MULTIPLYING:
                            try {
                                livingFlower
                                        .setMultiplying(Multiplying.fromValue(
                                                getXMLText(reader)));
                            } catch (UnknownTypeException eNew) {
                                throw new ParserException(eNew);
                            }
                            break;
                        case DISCOVERY_DATE:
                            livingFlower.setDiscoveryDate(DateParser.parseDate(
                                    getXMLText(reader)));
                            break;
                        case ORIGIN:
                            livingFlower.setOrigin(getXMLText(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerNameTag.fromValue(name)
                            == FlowerNameTag.LIVING_FLOWER) {
                        return livingFlower;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag wild flower.");
    }

    /**
     * @param reader xml stream reader.
     * @return abstract class object.
     * @throws XMLStreamException exception.
     * @throws ParserException    custom exception.
     */
    private ArtificialFlower buildArtificialFlower(final XMLStreamReader reader)
            throws XMLStreamException, ParserException {
        ArtificialFlower artificialFlower = new ArtificialFlower();
        /*attributes.*/
        artificialFlower.setId(Integer.parseInt(reader.getAttributeValue(null,
                FlowerNameTag.ID.getValue())));
        /*content.*/
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (FlowerNameTag.fromValue(name)) {
                        case MATERIAL:
                            artificialFlower.setMaterial((getXMLText(reader)));
                            break;
                        case NAME:
                            artificialFlower.setName(getXMLText(reader));
                            break;
                        case STEM_COLOR:
                            try {
                                artificialFlower.setStemColor(Color
                                        .fromValue(getXMLText(reader)));
                            } catch (UnknownTypeException eNew) {
                                throw new ParserException(eNew);
                            }
                            break;
                        case HEIGHT:
                            artificialFlower.setHeight(Integer
                                    .parseInt(getXMLText(reader)));
                            break;
                        case TEMPERATURE:
                            artificialFlower.setTemperature(getXMLText(reader));
                            break;
                        case DISCOVERY_DATE:
                            artificialFlower.setDiscoveryDate(DateParser
                                    .parseDate(getXMLText(reader)));
                            break;
                        case ORIGIN:
                            artificialFlower.setOrigin(getXMLText(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (FlowerNameTag.fromValue(name)
                            == FlowerNameTag.ARTIFICIAL_FLOWER) {
                        return artificialFlower;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag wild flower.");
    }

    /**
     * Element parse to text.
     *
     * @param reader xml reader.
     * @return string text.
     * @throws XMLStreamException exception.
     */
    private String getXMLText(final XMLStreamReader reader)
            throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
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
}
