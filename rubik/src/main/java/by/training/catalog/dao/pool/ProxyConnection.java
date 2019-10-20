package by.training.catalog.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Connection proxy class, instances of that created and provided by the
 * {@link ConnectionPool}. Delegates all method invocations to the real
 * connection except for close() method which puts ProxyConnection
 * to the connection pool. Method closeRealConnection() allows to close
 * proxied connection.
 */
public class ProxyConnection implements Connection {
    /**
     * Real connection. Most of the method invocations are delegated to it.
     */
    private Connection connection;

    /**
     * Creates ProxyConnection for chosen connection.
     *
     * @param connectionNew Connection that will be proxied.
     */
    ProxyConnection(final Connection connectionNew) {
        connection = connectionNew;
    }

    /**
     * Getter.
     *
     * @return connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Create statement.
     *
     * @return statement.
     * @throws SQLException sql exception.
     */
    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Prepare prepared statement.
     *
     * @param sql sql query.
     * @return prepared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql)
            throws SQLException {
        return connection.prepareStatement(sql);
    }

    /**
     * Prepare callable statement.
     *
     * @param sql sql query.
     * @return callable statement.
     * @throws SQLException sql exception.
     */
    @Override
    public CallableStatement prepareCall(final String sql) throws SQLException {
        return connection.prepareCall(sql);
    }

    /**
     * Native sql.
     *
     * @param sql sql query.
     * @return result.
     * @throws SQLException sql exception.
     */
    @Override
    public String nativeSQL(final String sql) throws SQLException {
        return connection.nativeSQL(sql);
    }

    /**
     * Setter for auto commit.
     *
     * @param autoCommit true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public void setAutoCommit(final boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    /**
     * Getter for auto commit.
     *
     * @return true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    /**
     * Commit.
     *
     * @throws SQLException sql exception.
     */
    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollback.
     *
     * @throws SQLException sql exception.
     */
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Sets autocommit to {@code true} and returns proxy to the pool.
     *
     * @throws SQLException If check for auto commit or auto commit set threw
     *                      SQLException.
     */
    @Override
    public void close() throws SQLException {
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        ConnectionPool.getInstance().releaseConnection(this);
    }

    /**
     * Check close or not.
     *
     * @return true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    /**
     * Getter for meta data.
     *
     * @return DB meta data.
     * @throws SQLException sql exception.
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    /**
     * Setter for read only.
     *
     * @param readOnly true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public void setReadOnly(final boolean readOnly) throws SQLException {
        connection.setReadOnly(readOnly);
    }

    /**
     * Getter for read only.
     *
     * @return true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    /**
     * Setter for catalog.
     *
     * @param catalog name of catalog.
     * @throws SQLException sql exception.
     */
    @Override
    public void setCatalog(final String catalog) throws SQLException {
        connection.setCatalog(catalog);
    }

    /**
     * Getter for catalog.
     *
     * @return name of catalog.
     * @throws SQLException sql exception.
     */
    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    /**
     * Setter for level transaction isolation.
     *
     * @param level transaction isolation.
     * @throws SQLException sql exception.
     */
    @Override
    public void setTransactionIsolation(final int level) throws SQLException {
        connection.setTransactionIsolation(level);
    }

    /**
     * Getter for level transaction isolation.
     *
     * @return level transaction isolation.
     * @throws SQLException sql exception.
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    /**
     * Getter for warnings.
     *
     * @return SQl warning.
     * @throws SQLException sql exception.
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    /**
     * Clear all warnings.
     *
     * @throws SQLException sql exception.
     */
    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    /**
     * Create statement.
     *
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @return statement.
     * @throws SQLException sql exception.
     */
    @Override
    public Statement createStatement(final int resultSetType,
                                     final int resultSetConcurrency)
            throws SQLException {
        return connection.createStatement();
    }

    /**
     * Prepare prepared statement with all parameters.
     *
     * @param sql                  sql query.
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @return prepared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int resultSetType,
                                              final int resultSetConcurrency)
            throws SQLException {
        return connection
                .prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Prepare callable statement with all parameters.
     *
     * @param sql                  sql query.
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @return callable statement.
     * @throws SQLException sql exception.
     */
    @Override
    public CallableStatement prepareCall(final String sql,
                                         final int resultSetType,
                                         final int resultSetConcurrency)
            throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
    }

    /**
     * Getter for type map.
     *
     * @return type map.
     * @throws SQLException sql exception.
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    /**
     * Setter for type map.
     *
     * @param map type map.
     * @throws SQLException sql exception.
     */
    @Override
    public void setTypeMap(final Map<String, Class<?>> map)
            throws SQLException {
        connection.setTypeMap(map);
    }

    /**
     * Setter for holdability.
     *
     * @param holdability holdability.
     * @throws SQLException sql exception.
     */
    @Override
    public void setHoldability(final int holdability) throws SQLException {
        connection.setHoldability(holdability);
    }

    /**
     * Getter for holdability.
     *
     * @return holdability.
     * @throws SQLException sql exception.
     */
    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    /**
     * Setter for savepoint.
     *
     * @return savepoint.
     * @throws SQLException sql exception.
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    /**
     * Setter for savepoint with name.
     *
     * @param name name of savepoint.
     * @return savepoint with name.
     * @throws SQLException sql exception.
     */
    @Override
    public Savepoint setSavepoint(final String name) throws SQLException {
        return connection.setSavepoint(name);
    }

    /**
     * Rollback.
     *
     * @param savepoint savepoint.
     * @throws SQLException sql exception.
     */
    @Override
    public void rollback(final Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    /**
     * Release savepoint.
     *
     * @param savepoint savepoint.
     * @throws SQLException sql exception.
     */
    @Override
    public void releaseSavepoint(final Savepoint savepoint)
            throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    /**
     * Create statement.
     *
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @param resultSetHoldability result set holdability.
     * @return statement.
     * @throws SQLException sql exception.
     */
    @Override
    public Statement createStatement(final int resultSetType,
                                     final int resultSetConcurrency,
                                     final int resultSetHoldability)
            throws SQLException {
        return connection.createStatement(resultSetType, resultSetConcurrency,
                resultSetHoldability);
    }

    /**
     * Prepare prepared statement.
     *
     * @param sql                  sql query.
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @param resultSetHoldability result set holdability.
     * @return prapared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int resultSetType,
                                              final int resultSetConcurrency,
                                              final int resultSetHoldability)
            throws SQLException {
        return connection
                .prepareStatement(sql, resultSetType, resultSetConcurrency,
                        resultSetHoldability);
    }

    /**
     * Prepare callable statement.
     *
     * @param sql                  sql query.
     * @param resultSetType        result set type.
     * @param resultSetConcurrency result set concurrency.
     * @param resultSetHoldability result set holdability.
     * @return callable statement.
     * @throws SQLException sql exception.
     */
    @Override
    public CallableStatement prepareCall(final String sql,
                                         final int resultSetType,
                                         final int resultSetConcurrency,
                                         final int resultSetHoldability)
            throws SQLException {
        return connection.prepareCall(sql, resultSetType, resultSetConcurrency,
                resultSetHoldability);
    }

    /**
     * Prepare prepared statement.
     *
     * @param sql               sql query.
     * @param autoGeneratedKeys auto generated keys.
     * @return prepared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int autoGeneratedKeys)
            throws SQLException {
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    /**
     * Prepare prepared statement.
     *
     * @param sql           sql query.
     * @param columnIndexes column indexes.
     * @return prepared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final int[] columnIndexes)
            throws SQLException {
        return connection.prepareStatement(sql, columnIndexes);
    }

    /**
     * Prepare prepared statement.
     *
     * @param sql         sql query.
     * @param columnNames column names.
     * @return prepared statement.
     * @throws SQLException sql exception.
     */
    @Override
    public PreparedStatement prepareStatement(final String sql,
                                              final String[] columnNames)
            throws SQLException {
        return connection.prepareStatement(sql, columnNames);
    }

    /**
     * Create clob.
     *
     * @return clob.
     * @throws SQLException sql exception.
     */
    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    /**
     * Create blob.
     *
     * @return blob.
     * @throws SQLException sql exception.
     */
    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    /**
     * Create NClob.
     *
     * @return nclob.
     * @throws SQLException sql exception.
     */
    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    /**
     * Create sql xml.
     *
     * @return SQLXML
     * @throws SQLException sql exception.
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    /**
     * Check valid by timeout.
     *
     * @param timeout timeout.
     * @return true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public boolean isValid(final int timeout) throws SQLException {
        return connection.isValid(timeout);
    }

    /**
     * Setter for client info.
     *
     * @param name  name.
     * @param value value.
     * @throws SQLClientInfoException sql client info exception.
     */
    @Override
    public void setClientInfo(final String name, final String value)
            throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    /**
     * Setter for client info.
     *
     * @param properties properties.
     * @throws SQLClientInfoException sql client info exception.
     */
    @Override
    public void setClientInfo(final Properties properties)
            throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    /**
     * Getter for client info.
     *
     * @param name name.
     * @return client info.
     * @throws SQLException sql exception.
     */
    @Override
    public String getClientInfo(final String name) throws SQLException {
        return connection.getClientInfo(name);
    }

    /**
     * Getter for client info.
     *
     * @return properties.
     * @throws SQLException sql exception.
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    /**
     * Create array of.
     *
     * @param typeName type name.
     * @param elements elements.
     * @return array.
     * @throws SQLException sql exception.
     */
    @Override
    public Array createArrayOf(final String typeName, final Object[] elements)
            throws SQLException {
        return connection.createArrayOf(typeName, elements);
    }

    /**
     * Create struct.
     *
     * @param typeName   type name.
     * @param attributes attributes.
     * @return struct.
     * @throws SQLException sql exception.
     */
    @Override
    public Struct createStruct(final String typeName, final Object[] attributes)
            throws SQLException {
        return connection.createStruct(typeName, attributes);
    }

    /**
     * Setter for schema.
     *
     * @param schema schema.
     * @throws SQLException sql exception.
     */
    @Override
    public void setSchema(final String schema) throws SQLException {
        connection.setSchema(schema);
    }

    /**
     * Getter for schema.
     *
     * @return schema.
     * @throws SQLException sql exception.
     */
    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    /**
     * Abort.
     *
     * @param executor executor.
     * @throws SQLException sql exception.
     */
    @Override
    public void abort(final Executor executor) throws SQLException {
        connection.abort(executor);
    }

    /**
     * Setter for network timeout.
     *
     * @param executor     executor.
     * @param milliseconds timeout in milliseconds.
     * @throws SQLException sql exception.
     */
    @Override
    public void setNetworkTimeout(final Executor executor,
                                  final int milliseconds)
            throws SQLException {
        connection.setNetworkTimeout(executor, milliseconds);
    }

    /**
     * Getter for network timeout.
     *
     * @return timeout in milliseconds.
     * @throws SQLException sql exception.
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    /**
     * Unwrap.
     *
     * @param iface class.
     * @param <T>   object.
     * @return object.
     * @throws SQLException sql exception.
     */
    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return connection.unwrap(iface);
    }

    /**
     * Check wrapper for class.
     *
     * @param iface class.
     * @return true or false.
     * @throws SQLException sql exception.
     */
    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return connection.isWrapperFor(iface);
    }
}
