package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;

import java.util.List;

/**
 * Rubik dao interface.
 */
public interface RubikDao extends Dao<RubiksCube> {
    /**
     * Find count of rubik's by unblocked.
     *
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    int findCountByUnblocked() throws PersistenceException;

    /**
     * Find rubik's by unblocked in range.
     *
     * @param limit  limit.
     * @param offset offset.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubiksByUnblocked(int limit, int offset)
            throws PersistenceException;

    /**
     * Find count of rubik's by model.
     *
     * @param model model.
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    int findCountByModel(String model) throws PersistenceException;

    int findCountBySize(String size) throws PersistenceException;

    /**
     * Find count of rubik's by form.
     *
     * @param form form.
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    int findCountByForm(String form) throws PersistenceException;

    /**
     * Find count of rubik's by price in range.
     *
     * @param min min price.
     * @param max max price.
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    int findCountByPrice(double min, double max)
            throws PersistenceException;

    /**
     * Find all rubik's by manufacturer in range.
     *
     * @param manufacturer manufacturer.
     * @param limit        limit.
     * @param offset       offset.
     * @return list of rubik's.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubiksByManufacturer(String manufacturer, int limit,
                                              int offset)
            throws PersistenceException;

    /**
     * Find rubiks by size in range.
     *
     * @param size   size of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws PersistenceException;

    /**
     * Find rubik by model.
     *
     * @param model  model.
     * @param limit  limit.
     * @param offset offset.
     * @return cube.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubikByModel(String model, int limit, int offset)
            throws PersistenceException;

    /**
     * Find rubik's by range price in range.
     *
     * @param minPrice minimum price.
     * @param maxPrice maximum price.
     * @param offset   offset.
     * @param limit    limit.
     * @return list of cubes.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubiksByRangePrice(double minPrice, double maxPrice,
                                            int offset, int limit)
            throws PersistenceException;

    /**
     * Find rubik's by form in range.
     *
     * @param form   form of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistenceException dao exception.
     */
    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws PersistenceException;

    /**
     * Read cube.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistenceException dao exception.
     */
    void read(RubiksCube rubiksCubeNew) throws PersistenceException;

    /**
     * Read all manufacturers.
     *
     * @return list of manufacturers.
     * @throws PersistenceException dao exception.
     */
    List<String> readAllManufacturer() throws PersistenceException;

    /**
     * Read all forms.
     *
     * @return list of forms.
     * @throws PersistenceException dao exception.
     */
    List<String> readAllForm() throws PersistenceException;

    /**
     * Read all plastic colors.
     *
     * @return list of plastic colors.
     * @throws PersistenceException dao exception.
     */
    List<String> readAllPlasticColor() throws PersistenceException;

    /**
     * Find count of rubik's by manufacturer.
     *
     * @param manufacturer manufacturer.
     * @return count of rubik's.
     * @throws PersistenceException dao exception.
     */
    int findCountByManufacturer(String manufacturer)
            throws PersistenceException;

    /**
     * Update state of cube. Ban.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistenceException dao exception.
     */
    void updateState(RubiksCube rubiksCubeNew)
            throws PersistenceException;
}
