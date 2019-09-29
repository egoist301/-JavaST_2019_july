package by.training.catalog.dao;

/**
 * Factory interface that is used to creates DAOs.
 */
public interface DaoFactory {
    UserDao createAccountDao(AbstractConnectionManager connectionManager);

    RubikDao createRubikDao(AbstractConnectionManager connectionManagerNew);

    StoreImageDao createStoreImageDao(
            AbstractConnectionManager connectionManagerNew);
}
