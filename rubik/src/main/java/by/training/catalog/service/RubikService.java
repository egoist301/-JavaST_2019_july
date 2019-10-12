package by.training.catalog.service;

import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikService extends Service {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws ServiceException;

    RubiksCube findRubikByModel(String model) throws ServiceException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws ServiceException;

    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws ServiceException;

    RubiksCube findById(long id) throws ServiceException;

    List<RubiksCube> findAll() throws ServiceException;

    List<RubiksCube> findAll(int offset, int limit) throws ServiceException;

    void update(RubiksCube entityNew) throws ServiceException;

    void create(RubiksCube entityNew) throws ServiceException;

    int findElementCount() throws ServiceException;

    List<String> readAllManufacturer() throws ServiceException;

    List<String> readAllForm() throws ServiceException;

    List<String> readAllPlasticColor() throws ServiceException;
}
