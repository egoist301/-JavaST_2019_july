package by.training.catalog.service.factory;

import by.training.catalog.bean.RubiksCube;
import by.training.catalog.validator.RubikValidator;

import java.util.Date;
import java.util.List;

/**
 * Rubik factory.
 */
public class RubikFactory {
    /**
     * Get valid cube or throw exception.
     *
     * @param parameters parameters for cube.
     * @return cube.
     */
    public RubiksCube createCube(List<String> parameters) {
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
            return null;
        }
    }
}
