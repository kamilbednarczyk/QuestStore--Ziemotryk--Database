package databaseAccess;

import models.Artifact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ArtifactsDAO implements IDAO<Artifact> {

    private DbConnector dbConnector = DbConnector.getInstance();


    public void add(Artifact toAdd) {
        String query = "INSERT INTO artifacts" +
                "(name, description, prize)" +
                "VALUES ('" + toAdd.getName() + "', '" + toAdd.getDescription() + "', " + toAdd.getPrize() + ");";

        dbConnector.executeUpdate(query);
        System.out.println("add works");
    }

    public Artifact get(int id) {
        Artifact artifact = null;

        String query = "SELECT\n" +
                "*\n" +
                "FROM artifacts\n" +
                "WHERE artifact_id  = " + id;

        ResultSet currentResultSet = dbConnector.getResultSetByQuery(query);

        try {
            currentResultSet.next();
            artifact = new Artifact(currentResultSet.getString("name"),
                    currentResultSet.getString("description"),
                    currentResultSet.getInt("prize"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artifact;
    }

    public List<Artifact> getAll() {
        return null;
    }

    public void update(int id, Artifact toUpdate) {

    }

    public void delete(int id) {
        String query = "DELETE FROM artifacts\n" +
                "WHERE artifact_id = " + id;

        dbConnector.executeUpdate(query);
        System.out.println("delete works");
    }
}
