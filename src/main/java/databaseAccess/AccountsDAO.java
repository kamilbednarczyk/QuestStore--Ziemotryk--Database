package databaseAccess;

import models.Account;

import java.util.List;

public class AccountsDAO implements IDAO<Account> {

    DbConnector dbConnector = DbConnector.getInstance();

    @Override
    public void add(Account toAdd) {

    }

    @Override
    public Account get(int id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public void update(int id, Account toUpdate) {

    }

    @Override
    public void delete(int id) {

    }
}
