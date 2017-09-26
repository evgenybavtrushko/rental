package by.it_academy.services.impl;

import by.it_academy.dao.UserDao;
import by.it_academy.dao.impl.UserDaoImpl;
import by.it_academy.entities.User;
import by.it_academy.services.ServiceException;
import by.it_academy.services.UserService;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private static volatile UserService INSTANCE = null;
    private UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User getByLogin(String login) {
        try {
            return userDao.getByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting User by login" + login);
        }
    }

    @Override
    public List<User> getAllActiveUsers() {
        try {
            return userDao.getAllActiveUsers();
        } catch (SQLException e) {
            throw new ServiceException("Error getting List<User> Active Users");
        }
    }

    @Override
    public boolean changePassword(long id, String pass, String newPassword) {
        try {
            return userDao.changePassword(id, pass, newPassword);
        } catch (SQLException e) {
            throw new ServiceException("Error change change password User id" + id);
        }
    }

    @Override
    public boolean changeEmail(long id, String newEmail) {
        try {
            return userDao.changeEmail(id, newEmail);
        } catch (SQLException e) {
            throw new ServiceException("Error change email User id" + id);
        }
    }

    @Override
    public boolean changeActive(long id, boolean active) {
        try {
            return userDao.changeActive(id, active);
        } catch (SQLException e) {
            throw new ServiceException("Error change active User id" + id);
        }
    }

    @Override
    public User create(String name, String password, String address, String email, String login ) {
        User user = new User();
        try {
            LOG.info("UserServiceImpl.create()");
            startTransaction();
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setAddress(address);
            user.setEmail(email);
            userDao.save(user);
            commit();
            return user;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error create user");
        }
    }

    @Override
    public User get(long id) {
        try {
            return userDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting User by id " + id);
        }
    }


    @Override
    public int delete(long id) {
        try {
            return userDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting User id" + id);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            return userDao.getAll();
        } catch (SQLException e) {
            throw new ServiceException("Error getting List<User>");
        }
    }
    public boolean checkLogin(String login) {
        try {
            return userDao.checkLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting checkLogin");
        }
    }
    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }
}

