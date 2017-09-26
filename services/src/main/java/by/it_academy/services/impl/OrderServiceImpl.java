package by.it_academy.services.impl;

import by.it_academy.dao.CarDAO;
import by.it_academy.dao.OrderDAO;
import by.it_academy.dao.UserDao;
import by.it_academy.dao.impl.CarDAOImpl;
import by.it_academy.dao.impl.OrderDAOImpl;
import by.it_academy.dao.impl.UserDaoImpl;
import by.it_academy.entities.Car;
import by.it_academy.entities.Order;
import by.it_academy.dto.OrderDTO;
import by.it_academy.services.OrderService;
import by.it_academy.services.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl extends AbstractService implements OrderService {
    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private static volatile OrderService INSTANCE = null;

    private OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private CarDAO carDAO = CarDAOImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();

    public List<OrderDTO> getAllDTO() {
        try {
            LOG.info("OrderServiceImpl.getAllDTO()");
            startTransaction();
            List<OrderDTO> list = orderDAO.getAllDTO();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getAllDTO");
        }
    }

    public List<OrderDTO> getAllNewOrdersDTO() {
        try {
            LOG.info("OrderServiceImpl.getAllNewOrdersDTO()");
            startTransaction();
            List<OrderDTO> list = orderDAO.getAllNewOrdersDTO();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getAllNewOrdersDTO");
        }
    }

    public List<OrderDTO> getByUserIdDTO(long id) {
        try {
            LOG.info("OrderServiceImpl.getByUserIdDTO()");
            startTransaction();
            List<OrderDTO> list = orderDAO.getByUserIdDTO(id);
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getByUserIdDTO");
        }
    }


    @Override
    public boolean specifyTheReasonForFailure(long id, String refusalReason) {
        try {
            LOG.info("OrderServiceImpl.specifyTheReasonForFailure()");
            startTransaction();
            orderDAO.specifyTheReasonForFailure(id, refusalReason);
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error specify The Reason For Failure");
        }
    }
    @Override
    public boolean indicateDamage(long id, String damages) {
        try {
            LOG.info("OrderServiceImpl.indicateDamage()");
            startTransaction();
            orderDAO.indicateDamage(id, damages);
            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error indicate Damage");
        }
    }

    @Override
    public List<Order> getByUserId(long id) {
        try {
            LOG.info("OrderServiceImpl.getByUserId()");
            startTransaction();
            List<Order> list = orderDAO.getByUserId(id);
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getByUserId");
        }
    }

    @Override
    public List<Order> getAllNewOrders() {
        try {
            LOG.info("OrderServiceImpl.getAllNewOrders()");
            startTransaction();
            List<Order> list = orderDAO.getAllNewOrders();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getAllNewOrders");
        }
    }

    @Override
    public boolean changeOrderStatus(long id, String status) {
        try {
            LOG.info("OrderServiceImpl.changeOrderStatus()");
            startTransaction();
            orderDAO.changeOrderStatus(id, status);

            commit();
            return true;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error changeOrderStatus");
        }
    }

    @Override
    public Order create(long userId, long carId, Date rental_start_date, int period) {
        Order order = new Order();
        try {
            LOG.info("OrderServiceImpl.create()");
            startTransaction();
            order.setUserId(userId);
            Car car = carDAO.get(carId);
            order.setCarId(carId);
            order.setRentalStartDate(rental_start_date);
            order.setPeriod(period);
            order.setTotal(car.getPricePerDay() * period);
            System.out.println(order);
            order = orderDAO.save(order);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error create Order");
        }
    }
    @Override
    public Order get(long id) {
        try {
            LOG.info("OrderServiceImpl.get()");
            startTransaction();
            Order order = orderDAO.get(id);
            commit();
            return order;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error get Order id: " + id);
        }
    }

    @Override
    public int delete(long id) {
        try {
            LOG.info("OrderServiceImpl.delete()");
            startTransaction();
            int x = orderDAO.delete(id);
            commit();
            return x;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error delete Order id: " + id);
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            LOG.info("OrderServiceImpl.getAll()");
            startTransaction();
            List<Order> list = orderDAO.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting List<Order> getAll");
        }
    }


    public static OrderService getInstance() {
        OrderService orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }

        return orderService;
    }
}
