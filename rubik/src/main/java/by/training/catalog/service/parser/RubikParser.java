package by.training.catalog.service.parser;

import by.training.catalog.bean.RubiksCube;

//TODO write this class
public class RubikParser {
    public RubiksCube getCube(final String model, final String size,
                              final String price, final String weight,
                              final String info, final String manufacturer,
                              final String form, final String plasticColor,
                              final String primaryPlastic) {
        RubiksCube rubiksCube = new RubiksCube(1);
        rubiksCube.setModel(model);
        rubiksCube.setInfo(info);

        return rubiksCube;
    }
}
