package databaseAccess;

import java.sql.*;

public class DbConnector {
    private static DbConnector instance = new DbConnector();
    private ConnectionPool connectionPool;

    public static void main(String[] args) {
        DbConnector dbConnector = DbConnector.getInstance();
    }

    private DbConnector() {
        try {
            this.connectionPool = ConnectionPool.create("jdbc:postgresql://localhost:5432/queststore_database", "queststore_user", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DbConnector getInstance() {
        return instance;
    }


    public ResultSet getCurrentResultSetByQuery(String query) {
        Connection connection = getConnectionFromConnectionPool();
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        releaseUsedConnection(connection);

        return resultSet;

    }


    private void executeUpdate(String query) {
        Connection connection = getConnectionFromConnectionPool();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        releaseUsedConnection(connection);
    }

    private Connection getConnectionFromConnectionPool() {
        return connectionPool.getConnection();
    }

    private void releaseUsedConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }
}
