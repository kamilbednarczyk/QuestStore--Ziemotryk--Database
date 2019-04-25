package databaseAccess;

import models.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsHistoryDAO implements IDAO<Transaction>{
    DbConnector dbConnector = DbConnector.getInstance();

    public void create() {
        dbConnector.executeUpdate(
                "CREATE TABLE transactions (" +
                        "transaction_id serial PRIMARY KEY," +
                        "buyer TEXT NOT NULL," +
                        "date TEXT NOT NULL," +
                        "artifact_name TEXT NOT NULL" +
                        ")"
        );
    }

    @Override
    public void add(Transaction toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO transactions\n"
                        + "(buyer, date, artifact_name)\n"
                        + "VALUES('" + toAdd.getBuyer() + "',\n"
                        + "'" + toAdd.getDate() + "',\n"
                        + "'" + toAdd.getArtifactName() + "'\n"
                        + ")"
        );
    }

    @Override
    public Transaction get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM transactions WHERE transaction_id=" + id
        );
        Transaction transaction = null;

        try {
            resultSet.next();
            transaction = new Transaction(
                    resultSet.getInt("transaction_id"),
                    resultSet.getString("buyer"),
                    resultSet.getString("date"),
                    resultSet.getString("artifact_name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM transactions"
        );

        try {
            while (resultSet.next()) {
                transactions.add(
                        new Transaction(
                                resultSet.getInt("transaction_id"),
                                resultSet.getString("buyer"),
                                resultSet.getString("date"),
                                resultSet.getString("artifact_name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void update(int id, Transaction toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE transactions\n"
                        + "SET buyer=" + toUpdate.getBuyer() + ",\n"
                        + "date=" + toUpdate.getDate() + ",\n"
                        + "artifact_name='" + toUpdate.getArtifactName() + "'\n"
                        + "WHERE account_id=" + id
        );
    }

    @Override
    public void delete(int id) {
        dbConnector.executeUpdate(
                "DELETE FROM transactions WHERE transaction_id=" + id
        );
    }
}
