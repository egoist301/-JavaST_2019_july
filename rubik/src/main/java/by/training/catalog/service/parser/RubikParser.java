package by.training.catalog.service.parser;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.service.ServiceException;
import by.training.catalog.validator.RubikValidator;

import java.util.Date;
import java.util.List;

/**
 * Rubik parser.
 */
public class RubikParser {
    /**
     * Get valid cube or throw exception.
     *
     * @param parameters parameters for cube.
     * @return cube.
     * @throws ServiceException if cube invalid.
     */
    public RubiksCube getCube(List<String> parameters) throws ServiceException {
        if (RubikValidator.isValid(parameters)) {
            RubiksCube rubiksCube = new RubiksCube(1);
            int i = 0;
            rubiksCube.setModel(parameters.get(i));
            rubiksCube.setPrice(Double.parseDouble(parameters.get(++i)));
            rubiksCube.setWeight(Double.parseDouble(parameters.get(++i)));
            rubiksCube.setInfo(parameters.get(++i));
            rubiksCube.setSize(parameters.get(++i));
            rubiksCube.setManufacturer(parameters.get(++i));
            rubiksCube.setForm(parameters.get(++i));
            rubiksCube.setPlasticColor(parameters.get(++i));
            rubiksCube.setPrimaryPlastic(parameters.get(++i) != null);
            rubiksCube.setDate(new Date());
            return rubiksCube;
        } else {
            throw new ServiceException();
        }
    }
}
