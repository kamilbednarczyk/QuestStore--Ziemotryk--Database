package databaseAccess;

import models.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements IDAO<Class> {

    private DbConnector dbConnector = DbConnector.getInstance();

    @Override
    public void add(Class toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO classes(class_name)\n"
                        + "VALUES ('" + toAdd.getClassName() + "');"
        );
    }

    @Override
    public Class get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM classes WHERE class_id="+id
        );
        Class questStoreClass = null;

        try {
            resultSet.next();
            questStoreClass = new Class(
                    resultSet.getInt("class_id"),
                    resultSet.getString("class_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questStoreClass;
    }

    @Override
    public List<Class> getAll() {
        List<Class> classes = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM classes"
        );

        try {
            while(resultSet.next()) {
                classes.add(
                        new Class(
                                resultSet.getInt("class_id"),
                                resultSet.getString("class_name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public void update(int id, Class toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE classes\n"
                        + "SET class_name=" + toUpdate.getClassName() + "\n"
                        + "WHERE class_id=" + id
        );
    }

    @Override
    public void delete(int id) {
        dbConnector.executeUpdate(
                "DELETE FROM classes WHERE class_id="+id
        );
    }

    public int getClassIdByName(String name) {
        List<Class> classes = getAll();

        for(Class currentClass: classes) {
            if(currentClass.getClassName().equals(name)) {
                return currentClass.getClassId();
            }
        }
        return -1;
    }
}
