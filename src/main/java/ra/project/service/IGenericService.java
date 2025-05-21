package ra.project.service;

import java.util.List;

public interface IGenericService <T, E, U, V>{
    List<T> findAll();

    T findById(E id);

    T create(U u);

    T update(E id, V v);

    void delete(E id);
}
