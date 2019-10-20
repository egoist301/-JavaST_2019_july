package by.training.catalog.service;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;

import java.util.List;

public interface RubikService {
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws ServiceException;

    List<RubiksCube> findRubiksByModel(String model, int limit, int offset)
            throws ServiceException;

    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws ServiceException;

    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws ServiceException;

    RubiksCube findById(long id) throws ServiceException;

    List<RubiksCube> findAll(int offset, int limit) throws ServiceException;

    void update(RubiksCube entityNew, List<RawData> rawDataNew)
            throws ServiceException;

    void create(RubiksCube entityNew) throws ServiceException;

    int findElementCount() throws ServiceException;

    void updateState(long id) throws ServiceException;

    List<String> readAllManufacturer() throws ServiceException;

    List<String> readAllForm() throws ServiceException;

    List<String> readAllPlasticColor() throws ServiceException;
}
