package databaseAccess;

import models.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountsDAO implements IDAO<Account> {

    DbConnector dbConnector = DbConnector.getInstance();

    @Override
    public void add(Account toAdd) {
        dbConnector.executeUpdate(
                "INSERT INTO accounts\n"
                        + "(login, password, permission)\n"
                        + "VALUES('" + toAdd.getLogin() + "',\n"
                        + "'" + toAdd.getPassword() + "',\n"
                        + "" + toAdd.getPermission() + "\n"
                        + ")"
        );
    }

    @Override
    public Account get(int id) {
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM accounts WHERE account_id=" + id
        );
        Account account = null;

        try {
            resultSet.next();
            account = new Account(
                    resultSet.getInt("account_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("permission")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        ResultSet resultSet = dbConnector.getResultSetByQuery(
                "SELECT * FROM accounts"
        );

        try {
            while (resultSet.next()) {
                accounts.add(
                        new Account(
                                resultSet.getInt("account_id"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getInt("permission")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void update(int id, Account toUpdate) {
        dbConnector.executeUpdate(
                "UPDATE accounts\n"
                        + "SET login=" + toUpdate.getLogin() + ",\n"
                        + "password=" + toUpdate.getPassword() + ",\n"
                        + "permission='" + toUpdate.getPermission() + "'\n"
                        + "WHERE account_id=" + id
        );
    }

    @Override
    public void delete(int id) {
        dbConnector.executeUpdate(
                "DELETE FROM accounts WHERE account_id=" + id
        );
    }

    public Account getAccountFromDbByAccountWithoutId(Account account) {
        List<Account> accounts = getAll();

        for (Account currentAccount : accounts) {
            if (currentAccount.getLogin().equals(account.getLogin()) &&
                    currentAccount.getPassword().equals(account.getPassword()) &&
                    currentAccount.getPermission() == account.getPermission()) {
                return currentAccount;
            }
        }
        throw new NoSuchElementException("Account not found");
    }
}
