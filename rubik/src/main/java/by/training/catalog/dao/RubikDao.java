package by.training.catalog.dao;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikDao extends Dao<RubiksCube> {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws PersistentException;

    RubiksCube findRubikByModel(String model) throws PersistentException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws PersistentException;

    List<RubiksCube> findRubiksByForm(Form form, int offset, int limit)
            throws PersistentException;

    int findRubiksCount() throws PersistentException;
}
