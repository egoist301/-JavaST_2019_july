package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikDao extends Dao<RubiksCube> {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws PersistentException;

    RubiksCube findRubikByModel(String model) throws PersistentException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws PersistentException;

    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws PersistentException;

    void read(RubiksCube rubiksCubeNew) throws PersistentException;

    List<String> readAllManufacturer() throws PersistentException;

    List<String> readAllForm() throws PersistentException;

    List<String> readAllPlasticColor() throws PersistentException;
}
