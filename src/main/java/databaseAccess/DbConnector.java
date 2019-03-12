package databaseAccess;

import java.sql.*;

public class DbConnector {
    private static DbConnector instance = new DbConnector();
    Connection connection;
    Statement statement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        DbConnector dbConnector = DbConnector.getInstance();
    }

    private DbConnector() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/queststore_database", "queststore_user", "123"); // set user and password
            statement = connection.createStatement();
            System.out.println("Opened Database Succefully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnector getInstance() {
        return instance;
    }


    public ResultSet getQurrentResultSet() {
        return resultSet;
    }

    private void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentResultSetByQuery(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
