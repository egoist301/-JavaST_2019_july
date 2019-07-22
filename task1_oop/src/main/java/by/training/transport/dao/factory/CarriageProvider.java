package by.training.transport.dao.factory;

import by.training.transport.bean.enums.CarriageType;
import by.training.transport.dao.factory.exception.CarriageValidationException;

import java.util.EnumMap;
import java.util.Map;

public class CarriageProvider {
    /**
     * Carriage factory map.
     */
    private Map<CarriageType, CarriageFactory> carriageFactoryMap
            = new EnumMap<>(CarriageType.class);

    /**
     * Default constructor.
     */
    public CarriageProvider() {
        carriageFactoryMap.put(CarriageType.COUPE, CoupeFactory.getFactory());
        carriageFactoryMap.put(CarriageType.PLACECART,
                PlacecartFactory.getFactory());
        carriageFactoryMap.put(CarriageType.SEATCARRIAGE,
                SeatCarriageFactory.getFactory());
    }

    /**
     * @param carriageType type of carriage.
     * @return carriage factory.
     * @throws CarriageValidationException if incorrect carriage type.
     */
    public CarriageFactory selectCarriageFactory(final String carriageType)
            throws CarriageValidationException {
        CarriageFactory carriageFactory;
        try {
            carriageFactory = carriageFactoryMap
                    .get(CarriageType.valueOf(carriageType.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new CarriageValidationException();
        }
        return carriageFactory;
    }
}
