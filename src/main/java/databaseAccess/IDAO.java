package databaseAccess;

import java.util.List;

public interface IDAO<E> {

    void add(E toAdd);

    E get(int id);

    List<E> getAll();

    void update(int id, E toUpdate);

    void delete(int id);
}
