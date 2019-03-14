package databaseAccess;

import models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO implements IDAO<Category> {

    DbConnector dbConnector =  DbConnector.getInstance();

    @Override
    public void add(Category toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO categories(category_name)\n"
                        + "VALUES ('" + toAdd.getCategoryId() + ");"
        );
    }

    @Override
    public Category get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM categories WHERE category_id="+id
        );
        Category category = null;

        try {
            resultSet.next();
            category = new Category(
                    resultSet.getInt("category_id"),
                    resultSet.getString("category_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM categories"
        );

        try {
            while(resultSet.next()) {
                categories.add(
                        new Category(
                                resultSet.getInt("category_id"),
                                resultSet.getString("category_name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void update(int id, Category toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE categories\n"
                        + "SET category_name=" + toUpdate.getCategoryName() + "\n"
                        + "WHERE backpack_id=" + id
        );
    }

    @Override
    public void delete(int id) {
        dbConnector.executeUpdate(
                "DELETE FROM categories WHERE category_id" + id
        );
    }
}
