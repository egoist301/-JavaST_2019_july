package by.training.catalog.service;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.RubiksCube;
import by.training.catalog.dao.PersistentException;

import java.util.List;

public interface RubikService extends Service<RubiksCube> {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws PersistentException;

    RubiksCube findRubikByModel(String model) throws PersistentException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws PersistentException;

    List<RubiksCube> findRubiksByForm(Form form, int offset, int limit)
            throws PersistentException;
}
