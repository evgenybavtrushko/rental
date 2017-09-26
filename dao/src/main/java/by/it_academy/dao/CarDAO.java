package by.it_academy.dao;

import by.it_academy.entities.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO extends DAO<Car> {
    List<Car> getAllActiveCar() throws SQLException;
    boolean changeToActive(long id) throws SQLException;
    boolean changeToInActive(long id) throws SQLException;

}
