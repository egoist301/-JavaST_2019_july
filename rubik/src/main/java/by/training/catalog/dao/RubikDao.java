package by.training.catalog.dao;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikDao extends Dao<RubiksCube> {
    List<RubiksCube> findRubiksBySize(String size) throws PersistenceException;

    RubiksCube findRubikByModel(String model) throws PersistenceException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice)
            throws PersistenceException;

    List<RubiksCube> findRubiksByForm(Form form) throws PersistenceException;

    int findRubiksCount() throws PersistenceException;
}
