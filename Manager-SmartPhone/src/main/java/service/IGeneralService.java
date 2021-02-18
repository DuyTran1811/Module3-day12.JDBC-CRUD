package service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> fillAll();

    void create(T t);

    boolean update(T t);

    boolean delete(int id);

    T fillById(int id);

    List<T> searchByIdName(String name);
}
