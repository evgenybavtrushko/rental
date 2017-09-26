package by.it_academy.services.impl;


import by.it_academy.db.ConnectionManager;
import by.it_academy.db.DbManagerException;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class AbstractService {

    public void startTransaction() throws SQLException {
        System.out.println(2222222);
        ConnectionManager.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
        ConnectionManager.getConnection().setAutoCommit(true);

    }

    public Connection getConnection() {
        return ConnectionManager.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DbManagerException("rollback error");
        }
    }
}
