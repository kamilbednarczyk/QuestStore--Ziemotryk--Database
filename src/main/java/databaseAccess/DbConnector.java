package databaseAccess;

import models.Artifact;

import java.sql.*;

public class DbConnector {
    private static DbConnector instance = new DbConnector();
    private ConnectionPool connectionPool;

    public static void main(String[] args) {
        DbConnector dbConnector = DbConnector.getInstance();
        dbConnector.getConnectionFromConnectionPool();
        Artifact artifact = new Artifact("NewLeniartek", "Newnew", 666);
        ArtifactsDAO artifactsDAO = new ArtifactsDAO();
        artifactsDAO.update(11, artifact);
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


    public ResultSet getResultSetByQuery(String query) {
        Connection connection = getConnectionFromConnectionPool();
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        releaseUsedConnection(connection);

        return resultSet;

    }


    public void executeUpdate(String query) {
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
