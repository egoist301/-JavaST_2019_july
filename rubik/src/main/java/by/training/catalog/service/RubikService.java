package by.training.catalog.service;

import by.training.catalog.bean.Form;
import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikService extends Service {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws ServiceException;

    RubiksCube findRubikByModel(String model) throws ServiceException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws ServiceException;

    List<RubiksCube> findRubiksByForm(Form form, int offset, int limit)
            throws ServiceException;

    RubiksCube findById(long id) throws ServiceException;

    List<RubiksCube> findAll() throws ServiceException;

    List<RubiksCube> findAll(int offset, int limit) throws ServiceException;

    void update(final RubiksCube entityNew) throws ServiceException;

    void create(final RubiksCube entityNew) throws ServiceException;

    int findElementCount() throws ServiceException;
}
