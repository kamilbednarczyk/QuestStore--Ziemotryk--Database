package databaseAccess;

import models.Quest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestsDAO implements IDAO<Quest> {

    private DbConnector dbConnector =  DbConnector.getInstance();

    @Override
    public void add(Quest toAdd) {
        String query = "INSERT INTO quests" +
                "(name, description, coolcoin_prize)" +
                "VALUES ('" + toAdd.getName() + "," +
                toAdd.getDescription() + "','" +
                toAdd.getCoolcoinPrize() + "');";

        dbConnector.executeUpdate(query);
        System.out.println("add works");
    }

    @Override
    public Quest get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM quests WHERE quest_id="+id
        );
        Quest quest = null;

        try {
            resultSet.next();
            quest = new Quest(
                    resultSet.getInt("quest_id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("coolcoin_prize")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quest;
    }

    @Override
    public List<Quest> getAll() {
        System.out.println("getting all quests...");
        List<Quest> questsList = new ArrayList<>();

        String query = "SELECT\n" +
                "*\n" +
                "FROM quests\n";

        ResultSet currentResultSet = dbConnector.getResultSetByQuery(query);

        try {
            while (currentResultSet.next()) {
                questsList.add(new Quest(
                        currentResultSet.getInt("quest_id"),
                        currentResultSet.getString("name"),
                        currentResultSet.getString("description"),
                        currentResultSet.getInt("coolcoin_prize")
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questsList;
    }

    @Override
    public void update(int id, Quest toUpdate) {
        String query = "UPDATE quests\n" +
                "SET name = '" + toUpdate.getName() + "', description = " + toUpdate.getDescription() +
                "', coolcoin_prize = " + toUpdate.getCoolcoinPrize() +"\n" +
                "WHERE account_id = " + id;
        dbConnector.executeUpdate(query);

        System.out.println("Updating quest...");
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM quests\n" +
                "WHERE quest_id = " + id;

        dbConnector.executeUpdate(query);

        System.out.println("Deleting quest...");
    }
}
