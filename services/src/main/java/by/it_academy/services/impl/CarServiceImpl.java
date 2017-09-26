package by.it_academy.services.impl;

import by.it_academy.dao.CarDAO;
import by.it_academy.dao.impl.CarDAOImpl;
import by.it_academy.entities.Car;
import by.it_academy.services.CarService;
import by.it_academy.services.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl extends AbstractService implements CarService {
    private static final Logger LOG = Logger.getLogger(CarServiceImpl.class);
    private static volatile CarService INSTANCE = null;
    private CarDAO carDAO = CarDAOImpl.getInstance();


    @Override
    public List<Car> getAllActiveCar() {
        try {
            LOG.info("CarServiceImpl.getAllActiveCar()");
            startTransaction();
            List<Car> list = carDAO.getAllActiveCar();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Car>");
        }
    }

    @Override
    public boolean changeToInActive(long id) {
        try {
            LOG.info("CarServiceImpl.changeToInActive()");
            startTransaction();
            carDAO.changeToInActive(id);
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error change To InActive");
        }
    }

    @Override
    public boolean changeToActive(long id) {
        try {
            LOG.info("CarServiceImpl.changeToActive()");
            startTransaction();
            carDAO.changeToActive(id);
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error change To Active");
        }
    }

    @Override
    public Car create(String carName, double pricePerDay, String image) {
        Car car = new Car();
        try {
            LOG.info("CarServiceImpl.save()");
            startTransaction();
            car.setCarName(carName);
            car.setPricePerDay(pricePerDay);
            car.setImage(image);
            carDAO.save(car);
            commit();
            return car;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error create Car");
        }
    }


    @Override
    public Car get(long id) {
        try {
            LOG.info("CarServiceImpl.get()");
            startTransaction();
            Car car = carDAO.get(id);
            commit();
            return car;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Car by id: " + id);
        }
    }

    @Override
    public int delete(long id) {
        try {
            LOG.info("CarServiceImpl.delete()");
            startTransaction();
            int x = carDAO.delete(id);
            commit();
            return x;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error deleting Car id: " + id);
        }
    }

    @Override
    public List<Car> getAll() {
        try {
            LOG.info("CarServiceImpl.getAll()");
            startTransaction();
            List<Car> list = carDAO.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting  List<Car> getAll" );
        }
    }
    public static CarService getInstance() {
        CarService carService = INSTANCE;
        if (carService == null) {
            synchronized (UserServiceImpl.class) {
                carService = INSTANCE;
                if (carService == null) {
                    INSTANCE = carService = new CarServiceImpl();
                }
            }
        }
        return carService;
    }
}
