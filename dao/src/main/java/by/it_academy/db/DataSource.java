package by.it_academy.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    private static volatile DataSource datasource;
    private ComboPooledDataSource cpds;

    private final String URL;
    private final String DRIVER;
    private final String USER;
    private final String PASSWORD;

    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        if (resourceBundle == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            DRIVER = "com.mysql.jdbc.Driver";
            System.out.println("Бандл для db не был инициализирован");
        } else {
            URL = resourceBundle.getString("url");
            USER = resourceBundle.getString("user");
            PASSWORD = resourceBundle.getString("password");
            DRIVER = resourceBundle.getString("driver");
        }
    }
    private DataSource() throws IOException, SQLException, PropertyVetoException {

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(DRIVER);
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER);
        cpds.setPassword(PASSWORD);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            synchronized (DataSource.class) {
                if (datasource == null) {
                    datasource = new DataSource();
                }
            }
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
