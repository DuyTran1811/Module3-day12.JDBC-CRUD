package com.jdbc.controller.service;

import java.util.List;

public interface IGeneralService <T>{
    void insertUser(T t);
    T selectUser(int id);
    List<T> selectAllUsers();
    boolean deleteUser(int id);
    boolean updateUser(T t);
    T getUserById(int id);
    void insertUsersStore(T t);
}
