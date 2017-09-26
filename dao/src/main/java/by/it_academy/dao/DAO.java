package by.it_academy.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    T save(T t) throws SQLException;

    T get(long id) throws SQLException;

    int delete(long id) throws SQLException;

    List<T> getAll() throws SQLException;
}
