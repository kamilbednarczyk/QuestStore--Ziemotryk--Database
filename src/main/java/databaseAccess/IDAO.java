package databaseAccess;

import java.util.List;

public interface IDAO<E> {

    public void add(E toAdd);
    public E get(int id);
    public List<E> getAll();
    public void update(int id, E toUpdate);
    public void delete(int id);
}
