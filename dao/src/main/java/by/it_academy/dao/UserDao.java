package by.it_academy.dao;

import by.it_academy.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends DAO<User> {

    User getByLogin(String login) throws SQLException;

    List<User> getAllActiveUsers() throws SQLException;

    boolean changePassword(long id, String password, String newPassword) throws SQLException;

    boolean changeEmail(long id, String newEmail) throws SQLException;

    boolean changeActive(long id, boolean active) throws SQLException;

    boolean checkLogin(String login) throws SQLException;
}
