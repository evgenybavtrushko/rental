package by.it_academy.dao.impl;

import by.it_academy.dao.CarDAO;
import by.it_academy.entities.Car;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl extends AbstractDao implements CarDAO {

    private static volatile CarDAO INSTANCE = null;

    private static final Logger LOG = Logger.getLogger(CarDAOImpl.class);


    private static final String SELECT_ALL_ACTIVE = "SELECT * FROM car WHERE car.active = true;";

    private static final String SET_INACTIVE = "UPDATE car SET car.active = false"
            + " where car.car_id=?;";
    private static final String SET_ACTIVE = "UPDATE `car` SET car.active = true"
            + " where car.car_id = ?;";
    private static final String ADD_NEW_CAR = "INSERT INTO car(car_name, price_per_day, image, active) "
            + "VALUES(?, ?, ? , true)";
    private static final String GET_CAR_QUERY = "SELECT * FROM car WHERE car.car_id=?;";

    private static final String DELETE_FROM_CAR = "DELETE FROM car WHERE car.car_id=?;";

    private static final String SELECT_ALL = "SELECT * FROM car;";

    private static final String UPDATE_CAR_NAME = "UPDATE `car` SET car.`car_name`=?"
            + " where car.`car_id`=?;";
    private static final String UPDATE_CAR_PRICE = "UPDATE `car` SET car.`price`=?"
            + " where car.`car_id`=?;";
    private static final String UPDATE_CAR_IMAGE = "UPDATE `car` SET car.`image`=?"
            + " where car.`car_id`=?;";

    private PreparedStatement psGetAllActiveCar;
    private PreparedStatement psSetInActive;
    private PreparedStatement psSetActive;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAllCar;

    private CarDAOImpl() {
    }

    public static CarDAO getInstance() {
        CarDAO carDAO = INSTANCE;
        if (carDAO == null) {
            synchronized (CarDAOImpl.class) {
                carDAO = INSTANCE;
                if (carDAO == null) {
                    INSTANCE = carDAO = new CarDAOImpl();
                }
            }
        }
        return carDAO;
    }

    @Override
    public List<Car> getAllActiveCar() throws SQLException {
        LOG.info("CarDaoImpl.getAllActive()");
        psGetAllActiveCar = prepareStatement(SELECT_ALL_ACTIVE);
        ResultSet rs = psGetAllActiveCar.executeQuery();
        List<Car> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateCar(rs));
        }
        return list;
    }

    @Override
    public boolean changeToInActive(long id) throws SQLException {
        LOG.info("CarDaoImpl.changeToInActive()");
        psSetInActive = prepareStatement(SET_INACTIVE);
        psSetInActive.setLong(1, id);
        psSetInActive.executeUpdate();
        return true;
    }

    @Override
    public boolean changeToActive(long id) throws SQLException {
        LOG.info("CarDaoImpl.changeToActive()");

        psSetActive = prepareStatement(SET_ACTIVE);
        psSetActive.setLong(1, id);
        psSetActive.executeUpdate();
        return true;
    }


    @Override
    public Car save(Car car) throws SQLException {
        LOG.info("CarDaoImpl.save()");

        psSave = prepareStatement(ADD_NEW_CAR, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, car.getCarName());
        psSave.setDouble(2, car.getPricePerDay());
        psSave.setString(3, car.getImage());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            car.setCarId(rs.getLong(1));
        }
        close(rs);
        return car;
    }

    @Override
    public Car get(long id) throws SQLException {
        LOG.info("CarDaoImpl.get()");
        psGet = prepareStatement(GET_CAR_QUERY);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateCar(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public int delete(long id) throws SQLException {
        LOG.info("CarDaoImpl.delete()");

        psDelete = prepareStatement(DELETE_FROM_CAR);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Car> getAll() throws SQLException {
        LOG.info("CarDaoImpl.getAll()");

        psGetAllCar = prepareStatement(SELECT_ALL);
        ResultSet rs = psGetAllCar.executeQuery();
        List<Car> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateCar(rs));
        }
        return list;
    }


    private Car populateCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setCarId(rs.getLong(1));
        car.setCarName(rs.getString(2));
        car.setPricePerDay(rs.getDouble(3));
        car.setActive(rs.getBoolean(4));
        car.setImage(rs.getString(5));
        return car;
    }
}
