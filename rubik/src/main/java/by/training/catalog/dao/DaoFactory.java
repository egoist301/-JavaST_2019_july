package by.training.catalog.dao;

/**
 * Factory interface that is used to creates DAOs.
 */
public interface DaoFactory {
    /**
     * Create user dao.
     *
     * @param connectionManager connection manager.
     * @return user dao.
     */
    UserDao createAccountDao(AbstractConnectionManager connectionManager);

    /**
     * Create rubik dao.
     *
     * @param connectionManagerNew connection manager.
     * @return rubik dao.
     */
    RubikDao createRubikDao(AbstractConnectionManager connectionManagerNew);

    /**
     * Create store image dao.
     *
     * @param connectionManagerNew connection manager.
     * @return store image dao.
     */
    StoreImageDao createStoreImageDao(
            AbstractConnectionManager connectionManagerNew);
}
