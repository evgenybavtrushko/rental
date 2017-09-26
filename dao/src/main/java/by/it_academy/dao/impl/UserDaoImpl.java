package by.it_academy.dao.impl;

import by.it_academy.dao.UserDao;
import by.it_academy.entities.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static final Logger LOG = Logger.getLogger(CarDAOImpl.class);


    private static volatile UserDao INSTANCE = null;

    private static final String SAVE_USER_QUERY =
            "INSERT INTO user (name, login, password, address, email) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_USER_QUERY =
            "SELECT * FROM user WHERE user_id = ?";
    private static final String DELETE_USER_QUERY =
            " DELETE FROM user WHERE user_id= ?";
    private static final String GET_ALL_USERS_QUERY =
            "SELECT * FROM user";
    private static final String GET_ALL_ACTIVE_USERS_QUERY =
            "SELECT * FROM user WHERE active = true";
    private static final String CHANGE_PASSWORD_QUERY = "UPDATE user SET password = ? "
            + "where user_id =? ;";
    private static final String CHANGE_EMAIL_QUERY = "UPDATE user SET email=? "
            + "where user_id =?;";
    private static final String SET_INACTIVE_QUERY = "UPDATE user SET active = false"
            + " where user_id=?;";
    private static final String SET_ACTIVE_QUERY = "UPDATE user SET active = true"
            + " where user_id =?;";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN=?";

    private PreparedStatement psGetByLogin;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAllUsers;
    private PreparedStatement psGetAllActiveUsers;
    private PreparedStatement psChangePassword;
    private PreparedStatement psChangeEmail;
    private PreparedStatement psSetInActive;
    private PreparedStatement psSetActive;

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }


    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(GET_USER_BY_LOGIN);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return populateUser(rs);

        }
        close(rs);

        return null;
    }

    @Override
    public List<User> getAllActiveUsers() throws SQLException {
        psGetAllActiveUsers = prepareStatement(GET_ALL_ACTIVE_USERS_QUERY);
        ResultSet rs = psGetAllActiveUsers.executeQuery();
        System.out.println("fff");
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateUser(rs));
        }
        return list;
    }

    @Override
    public boolean changePassword(long id, String pass, String newPassword) throws SQLException {
        psChangePassword = prepareStatement(GET_USER_QUERY);
        psChangePassword.setLong(1, id);
        ResultSet rs = psChangePassword.executeQuery();
        rs.next();
        String password = rs.getString("password");
        if (password.equals(pass)) {
            psChangePassword = prepareStatement(CHANGE_PASSWORD_QUERY);
            psChangePassword.setString(1, newPassword);
            psChangePassword.setLong(2, id);
            psChangePassword.executeUpdate();
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean changeEmail(long id, String newEmail) throws SQLException {
        psChangeEmail = prepareStatement(CHANGE_EMAIL_QUERY);
        psChangeEmail.setString(1, newEmail);
        psChangeEmail.setLong(2, id);
        psChangeEmail.executeUpdate();
        return true;
    }

    @Override
    public boolean changeActive(long id, boolean active) throws SQLException {
        if (active == true) {
            psSetActive = prepareStatement(SET_INACTIVE_QUERY);
            psSetActive.setLong(1, id);
            psSetActive.executeUpdate();
            return true;
        } else if (active == false) {
            psSetInActive = prepareStatement(SET_ACTIVE_QUERY);
            psSetInActive.setLong(1, id);
            psSetInActive.executeUpdate();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User save(User user) throws SQLException {
        LOG.info("UserDaoImpl.save()");

        psSave = prepareStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, user.getName());
        psSave.setString(2, user.getLogin());
        psSave.setString(3, user.getPassword());
        psSave.setString(4, user.getAddress());
        psSave.setString(5, user.getEmail());
        LOG.info("UserDaoImpl.save1()");

        psSave.executeUpdate();
        LOG.info("UserDaoImpl.save2()");

        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }

    @Override
    public User get(long id) throws SQLException {
        psGet = prepareStatement(GET_USER_QUERY);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateUser(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(DELETE_USER_QUERY);
        psDelete.setLong(1, (long) id);
        return  psDelete.executeUpdate();
    }

    @Override
    public List<User> getAll() throws SQLException {
        psGetAllUsers = prepareStatement(GET_ALL_USERS_QUERY);
        ResultSet rs = psGetAllUsers.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateUser(rs));
        }
        return list;
    }
    public boolean checkLogin(String login) throws SQLException {
        LOG.info("CLientDaoImpl.checkLogin()");
        psGetByLogin = prepareStatement(GET_USER_BY_LOGIN);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    private User populateUser(ResultSet rs) throws SQLException {
        User user;
        user = new User();
        user.setId(rs.getLong("user_id"));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setActive(rs.getBoolean("active"));
        user.setAddress(rs.getString("address"));
        user.setEmail(rs.getString("email"));
        user.setType(rs.getString("type"));
        return user;
    }
}