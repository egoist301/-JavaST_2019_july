package by.training.catalog.service;

import by.training.catalog.bean.RawData;
import by.training.catalog.bean.RubiksCube;

import java.util.List;

/**
 * Service for rubik.
 */
public interface RubikService {
    /**
     * Find rubik's by manufacturer in range.
     *
     * @param manufacturer manufacturer.
     * @param limit        limit.
     * @param offset       offset.
     * @return list of rubik's.
     * @throws ServiceException service exception.
     */
    List<RubiksCube> findRubiksByManufacturer(String manufacturer,
                                              int limit,
                                              int offset)
            throws ServiceException;

    /**
     * Find rubik's by size.
     *
     * @param size   size of rubik.
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws ServiceException if catch Persistent exception.
     */
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws ServiceException;

    /**
     * Find rubik's by model.
     *
     * @param model  model.
     * @param limit  limit.
     * @param offset offset.
     * @return list of rubik's.
     * @throws ServiceException service exception.
     */
    List<RubiksCube> findRubiksByModel(String model, int limit, int offset)
            throws ServiceException;

    /**
     * Find all rubik's by range price in range.
     *
     * @param minPrice minimum.
     * @param maxPrice maximum.
     * @param offset   offset.
     * @param limit    limit.
     * @return list of rubik's.
     * @throws ServiceException service exception.
     */
    List<RubiksCube> findRubiksByRangePrice(String minPrice, String maxPrice,
                                            int offset, int limit)
            throws ServiceException;

    /**
     * Find count of rubik's by form.
     *
     * @param form form.
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    int findCountByForm(String form) throws ServiceException;

    /**
     * Find count of rubik's by price in range.
     *
     * @param min min price.
     * @param max max price.
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    int findCountByPrice(String min, String max)
            throws ServiceException;

    /**
     * Find count of rubik's by manufacturer.
     *
     * @param manufacturer manufacturer.
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    int findCountByManufacturer(String manufacturer)
            throws ServiceException;

    /**
     * Find rubik's by form.
     *
     * @param form   form.
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws ServiceException service exception.
     */
    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws ServiceException;

    /**
     * Find rubik by id.
     *
     * @param id id of cube.
     * @return rubik.
     * @throws ServiceException service exception.
     */
    RubiksCube findById(long id) throws ServiceException;

    /**
     * Find all rubik's in range.
     *
     * @param offset offset.
     * @param limit  limit.
     * @return list of rubik's.
     * @throws ServiceException service exception.
     */
    List<RubiksCube> findAll(int offset, int limit) throws ServiceException;

    /**
     * Edit cube.
     *
     * @param parameters parameters of cube.
     * @throws ServiceException service exception.
     */
    void update(long id, List<String> parameters)
            throws ServiceException;

    /**
     * Create cube by parameters and images.
     *
     * @param parameters parameters of cube.
     * @param rawDataNew raw data.
     * @throws ServiceException service exception.
     */
    void create(List<String> parameters, List<RawData> rawDataNew)
            throws ServiceException;

    /**
     * Find count of rubik's.
     *
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    int findElementCount() throws ServiceException;

    /**
     * Find count of rubik's by model.
     *
     * @param model model.
     * @return count of rubik's.
     * @throws ServiceException service exception.
     */
    int findCountByModel(String model) throws ServiceException;

    /**
     * Ban.
     *
     * @param id id of cube.
     * @throws ServiceException service exception.
     */
    void updateState(long id) throws ServiceException;

    /**
     * Read all manufacturers.
     *
     * @return list of manufacturers.
     * @throws ServiceException service exception.
     */
    List<String> readAllManufacturer() throws ServiceException;

    /**
     * Read all forms.
     *
     * @return list of forms.
     * @throws ServiceException service exception.
     */
    List<String> readAllForm() throws ServiceException;

    /**
     * Read all colors of plastic.
     *
     * @return list of plastic colors.
     * @throws ServiceException service exception.
     */
    List<String> readAllPlasticColor() throws ServiceException;
}
