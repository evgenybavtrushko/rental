package by.it_academy.services;

import by.it_academy.entities.User;

import java.util.List;

public interface UserService {

    User getByLogin(String login);

    List<User> getAllActiveUsers();

    boolean changePassword(long id, String pass, String newPassword);

    boolean changeEmail(long id, String newEmail);

    boolean changeActive(long id, boolean active);

    User create(String name, String password, String address, String email, String login);

    User get(long id);

    int delete(long id);

    List<User> getAll();

    public boolean checkLogin(String login);

}

