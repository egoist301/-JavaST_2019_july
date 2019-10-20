package by.training.catalog.dao;

import by.training.catalog.bean.RubiksCube;

import java.util.List;

/**
 * Rubik dao interface.
 */
public interface RubikDao extends Dao<RubiksCube> {
    /**
     * Find rubiks by size in range.
     *
     * @param size   size of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistentException dao exception.
     */
    List<RubiksCube> findRubiksBySize(String size, int offset, int limit)
            throws PersistentException;

    /**
     * Find rubik by model.
     *
     * @param model model.
     * @param limit limit.
     * @param offset offset.
     * @return cube.
     * @throws PersistentException dao exception.
     */
    List<RubiksCube> findRubikByModel(String model, int limit, int offset)
            throws PersistentException;

    /**
     * Find rubik's by range price in range.
     *
     * @param minPrice minimum price.
     * @param maxPrice maximum price.
     * @param offset   offset.
     * @param limit    limit.
     * @return list of cubes.
     * @throws PersistentException dao exception.
     */
    List<RubiksCube> findRubiksByRangePrice(int minPrice, int maxPrice,
                                            int offset, int limit)
            throws PersistentException;

    /**
     * Find rubik's by form in range.
     *
     * @param form   form of cube.
     * @param offset offset.
     * @param limit  limit.
     * @return list of cubes.
     * @throws PersistentException dao exception.
     */
    List<RubiksCube> findRubiksByForm(String form, int offset, int limit)
            throws PersistentException;

    /**
     * Read cube.
     *
     * @param rubiksCubeNew cube.
     * @throws PersistentException dao exception.
     */
    void read(RubiksCube rubiksCubeNew) throws PersistentException;

    /**
     * Read all manufacturers.
     *
     * @return list of manufacturers.
     * @throws PersistentException dao exception.
     */
    List<String> readAllManufacturer() throws PersistentException;

    /**
     * Read all forms.
     *
     * @return list of forms.
     * @throws PersistentException dao exception.
     */
    List<String> readAllForm() throws PersistentException;

    /**
     * Read all plastic colors.
     *
     * @return list of plastic colors.
     * @throws PersistentException dao exception.
     */
    List<String> readAllPlasticColor() throws PersistentException;

    /**
     * Update state of cube. Ban.
     * @param rubiksCubeNew cube.
     * @throws PersistentException dao exception.
     */
    void updateState(RubiksCube rubiksCubeNew)
            throws PersistentException;
}
