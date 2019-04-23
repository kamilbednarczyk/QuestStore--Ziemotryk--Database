package databaseAccess;

import models.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelsDAO implements IDAO<Level> {

    DbConnector dbConnector = DbConnector.getInstance();

    @Override
    public void add(Level toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO levels"
                        + "(level, coolcoins_needed, level_name, level_description)"
                        + "VALUES(" + toAdd.getLevel() + ",\n"
                        + "" + toAdd.getCoolcoinsNeeded() + ",\n"
                        + "'" + toAdd.getLevelName() + "',\n"
                        + "'" + toAdd.getLevelDescription() + "'"
                        + ");"
        );
    }

    @Override
    public Level get(int levelRow) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM levels WHERE level=" + levelRow
        );
        Level level = null;

        try {
            resultSet.next();
            level = new Level(
                    resultSet.getInt("level"),
                    resultSet.getInt("coolcoins_needed"),
                    resultSet.getString("level_name"),
                    resultSet.getString("level_description")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return level;
    }

    @Override
    public List<Level> getAll() {
        List<Level> levels = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM levels"
        );

        try {
            while (resultSet.next()) {
                levels.add(
                        new Level(
                                resultSet.getInt("level"),
                                resultSet.getInt("coolcoins_needed"),
                                resultSet.getString("level_name"),
                                resultSet.getString("level_description")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    @Override
    public void update(int level, Level toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE levels"
                        + "SET level=" + toUpdate.getLevel() + ",\n"
                        + "coolcoins_needed=" + toUpdate.getCoolcoinsNeeded() + ",\n"
                        + "level_name='" + toUpdate.getLevelName() + "',\n"
                        + "level_description='" + toUpdate.getLevelDescription()
        );
    }

    @Override
    public void delete(int level) {
        dbConnector.executeUpdate(
                "DELETE FROM levels WHERE level=" + level
        );
    }

    public Level getLevelBy(int coolcoins) {
        List<Level> listOfLevels = getAll();

        Level level = null;

        for (int i = 0; i < listOfLevels.size(); i++) {
            if (listOfLevels.get(i).getCoolcoinsNeeded() > coolcoins) {
                level = listOfLevels.get(i - 1);
            }
        }

        return level;
    }
}
