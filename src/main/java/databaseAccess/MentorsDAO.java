package databaseAccess;

import models.Mentor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorsDAO implements IDAO<Mentor> {

    private DbConnector dbConnector =  DbConnector.getInstance();

    @Override
    public void add(Mentor toAdd) {
        String query = "INSERT INTO mentors" +
                "(account_id, full_name, email, class_id, about, avatar_file)" +
                "VALUES (" + toAdd.getAccountId() + ",'" + toAdd.getFullName() + "', '" + toAdd.getEmail() + "', " + toAdd.getClassId() + ",'" +
                toAdd.getAbout() + "','" + toAdd.getAvatarFile() + "');";

        dbConnector.executeUpdate(query);
        System.out.println("add works");
    }

    @Override
    public Mentor get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM mentors WHERE account_id="+id
        );
        Mentor mentor = null;

        try {
            resultSet.next();
            mentor = new Mentor(
                    resultSet.getInt("account_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getInt("class_id"),
                    resultSet.getString("about"),
                    resultSet.getString("avatar_file")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mentor;
    }

    @Override
    public List<Mentor> getAll() {
        List<Mentor> mentorsList = new ArrayList<>();

        String query = "SELECT\n" +
                "*\n" +
                "FROM mentors\n";

        ResultSet currentResultSet = dbConnector.getResultSetByQuery(query);

        try {
            while (currentResultSet.next()) {
                mentorsList.add(new Mentor(
                        currentResultSet.getInt("account_id"),
                        currentResultSet.getString("full_name"),
                        currentResultSet.getString("email"),
                        currentResultSet.getInt("class_id"),
                        currentResultSet.getString("about"),
                        currentResultSet.getString("avatar_file")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mentorsList;
    }

    @Override
    public void update(int id, Mentor toUpdate) {
        String query = "UPDATE mentors\n" +
                "SET account_id = '" + toUpdate.getAccountId() + "', full_name = '" + toUpdate.getFullName() + "', email = " + toUpdate.getEmail() +
                "', class_id = " + toUpdate.getClassId() + "', about = '" + toUpdate.getAbout() + "', avatar_file = '" + toUpdate.getAvatarFile() +"\n" +
                "WHERE account_id = " + id;
        dbConnector.executeUpdate(query);
        System.out.println("dupa");
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM mentors\n" +
                "WHERE account_id = " + id;

        dbConnector.executeUpdate(query);
        System.out.println("delete works");
    }
}
