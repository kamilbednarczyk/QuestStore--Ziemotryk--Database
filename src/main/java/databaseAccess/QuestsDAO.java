package databaseAccess;

import models.Quest;

import java.util.List;

public class QuestsDAO implements IDAO<Quest> {

    DbConnector dbConnector =  DbConnector.getInstance();

    @Override
    public void add(Quest toAdd) {

    }

    @Override
    public Quest get(int id) {
        return null;
    }

    @Override
    public List<Quest> getAll() {
        return null;
    }

    @Override
    public void update(int id, Quest toUpdate) {

    }

    @Override
    public void delete(int id) {

    }
}
