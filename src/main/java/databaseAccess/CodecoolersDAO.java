package databaseAccess;

import models.Class;
import models.Codecooler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CodecoolersDAO implements IDAO<Codecooler> {

    DbConnector dbConnector = DbConnector.getInstance();

    @Override
    public void add(Codecooler toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO codecoolers(backpack_id, full_name, email, avatar_file, coolcoins)\n"
                        + "VALUES ('" + toAdd.getBackpackId() + "'\n"
                        + "'" + toAdd.getFullName() + "'\n"
                        + "'" + toAdd.getEmail() + "'\n"
                        + "'" + toAdd.getAvatarFile() + "'\n"
                        + "" + toAdd.getCoolcoins() + "\n"
                        +");"
        );
    }

    @Override
    public Codecooler get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM categories WHERE category_id="+id
        );
        Codecooler codecooler = null;

        try {
            resultSet.next();
            codecooler = new Codecooler(
                    resultSet.getInt("account_id"),
                    resultSet.getInt("class_id"),
                    resultSet.getInt("backpack_id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("email"),
                    resultSet.getString("avatar_file"),
                    resultSet.getInt("coolcoins")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codecooler;
    }

    @Override
    public List<Codecooler> getAll() {
        List<Codecooler> codecoolers = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM codecoolers"
        );

        try {
            while(resultSet.next()) {
                codecoolers.add(
                        new Codecooler(
                                resultSet.getInt("account_id"),
                                resultSet.getInt("class_id"),
                                resultSet.getInt("backpack_id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("email"),
                                resultSet.getString("avatar_file"),
                                resultSet.getInt("coolcoins")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codecoolers;
    }

    @Override
    public void update(int id, Codecooler toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE codecoolers\n"
                        + "SET class_id=" + toUpdate.getClassId() + ",\n"
                        + "backpack_id=" + toUpdate.getBackpackId() + ",\n"
                        + "full_name='" + toUpdate.getEmail() + "'\n"
                        + "email='" + toUpdate.getAvatarFile() + "'\n"
                        + "coolcoins=" + toUpdate.getCoolcoins() + "\n"
                        + "WHERE class_id=" + id
        );
    }

    @Override
    public void delete(int id) {
        dbConnector.executeUpdate(
                "DELETE FROM codecoolers WHERE account_id="+id
        );
    }
}
