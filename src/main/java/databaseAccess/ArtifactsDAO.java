package databaseAccess;

import models.Artifact;

import java.util.List;

public class ArtifactsDAO implements IDAO<Artifact> {

    DbConnector dbConnector = DbConnector.getInstance();

    public void add(Artifact toAdd) {
        String query = "INSERT INTO artifacts" +
                       "(name, description, prize)" +
                       "VALUES ('" + toAdd.getName() + "', '" + toAdd.getDescription() + "', " + toAdd.getPrize() + ");";
        dbConnector.executeUpdate(query);
        System.out.println("add works");
    }

    public Artifact get(int id) {
        return null;
    }

    public List<Artifact> getAll() {
        return null;
    }

    public void update(int id, Artifact toUpdate) {

    }

    public void delete(int id) {
        String query = "DELETE FROM artifacts\n" +
                       "WHERE artifact_id = " + String.valueOf(id);

        dbConnector.executeUpdate(query);
        System.out.println("delete works");
    }
}
