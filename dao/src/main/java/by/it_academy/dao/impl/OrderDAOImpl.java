package by.it_academy.dao.impl;

import by.it_academy.dao.OrderDAO;
import by.it_academy.entities.Order;
import by.it_academy.dto.OrderDTO;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl extends AbstractDao implements OrderDAO {

    private static volatile OrderDAO instance = null;


    private static final Logger LOG = Logger.getLogger(CarDAOImpl.class);


    private static final String SET_REFUSAL_REASON = "UPDATE orders SET orders.refusal_reason = ? "
            + " where orders.order_id =?;";

    private static final String SET_DAMAGES = "UPDATE orders SET orders.damages = ? "
            + " where orders.order_id =?;";

    private static final String GET_BY_USER_ID = "SELECT * FROM orders WHERE orders.user_id = ?";

    private static final String GET_ALL_NEW_ORDERS = "SELECT * FROM orders WHERE orders.status = NEW";

    private static final String CHANGE_ORDER_STATUS = "UPDATE orders SET orders.status = ? " +
            "WHERE orders.order_id = ?";

    private static final String ADD_NEW_ORDER = "INSERT INTO orders (user_id, car_id, total, " +
            " rental_start_date, period, date) VALUES (?, ?, ?, ?, ?,now())";

    private static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE orders.order_id = ?";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE orders.order_id =?";

    private static final String GET_All_ORDERS = "SELECT * FROM orders";


    private static final String GET_All_ORDERS_DTO = "SELECT order_id, name, car_name, total, date, rental_start_date, " +
            "period, refusal_reason, damages, status " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.user_id " +
            "JOIN car c ON o.car_id = c.Car_id";


    private static final String GET_BY_USER_ID_DTO = "SELECT ORDER_ID, NAME, CAR_NAME, TOTAL, DATE, RENTAL_START_DATE," +
            "PERIOD, REFUSAL_REASON, DAMAGES, STATUS " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.user_id " +
            "JOIN car c ON o.car_id = c.Car_id " +
            "WHERE o.user_id = ?";

    private static final String GET_ALL_NEW_ORDERS_DTO = "SELECT ORDER_ID, NAME, CAR_NAME, TOTAL, DATE, RENTAL_START_DATE," +
            "PERIOD, REFUSAL_REASON, DAMAGES, STATUS " +
            "FROM orders o " +
            "JOIN user u ON o.user_id = u.user_id " +
            "JOIN car c ON o.car_id = c.Car_id " +
            "WHERE o.status = 'NEW'";






    private PreparedStatement psSpecifyTheReasonForFailure;
    private PreparedStatement psIndicateDamage;
    private PreparedStatement psGetByUserId;
    private PreparedStatement psGetAllNewOrders;
    private PreparedStatement psChangeOrderStatus;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psDelete;
    private PreparedStatement psGetAll;

    private PreparedStatement psGetAllDTO;
    private PreparedStatement psGetByUserIdDTO;
    private PreparedStatement psGetAllNewOrdersDTO;


    private OrderDAOImpl() {

    }

    public static OrderDAO getInstance() {
        OrderDAO orderDAO = instance;
        if (orderDAO == null) {
            synchronized (CarDAOImpl.class) {
                orderDAO = instance;
                if (orderDAO == null) {
                    instance = orderDAO = new OrderDAOImpl();
                }
            }
        }
        return orderDAO;
    }


    public List<OrderDTO> getAllDTO() throws SQLException {
        LOG.info("OrderDAOImpl.getAllDTO()");
        psGetAllDTO = prepareStatement(GET_All_ORDERS_DTO);
        ResultSet rs = psGetAllDTO.executeQuery();
        List<OrderDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrderDTO(rs));
        }
        close(rs);
        return list;
    }

    public List<OrderDTO> getByUserIdDTO(long id) throws SQLException {
        LOG.info("OrderDAOImpl.getByUserIdDTO()");

        psGetByUserIdDTO = prepareStatement(GET_BY_USER_ID_DTO);
        psGetByUserIdDTO.setLong(1, (long) id);
        ResultSet rs = psGetByUserIdDTO.executeQuery();
        List<OrderDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrderDTO(rs));
        }
        close(rs);
        return list;
    }

    public List<OrderDTO> getAllNewOrdersDTO() throws SQLException {
        LOG.info("OrderDAOImpl.getAllNewOrdersDTO()");

        psGetAllNewOrdersDTO = prepareStatement(GET_ALL_NEW_ORDERS_DTO);
        ResultSet rs = psGetAllNewOrdersDTO.executeQuery();
        List<OrderDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrderDTO(rs));
        }
        close(rs);
        return list;
    }

    private OrderDTO populateOrderDTO(ResultSet rs) throws SQLException {
        LOG.info("OrderDAOImpl.populateOrderDTO()");
        OrderDTO order = new OrderDTO();
        order.setOrderId(rs.getLong(1));
        order.setName(rs.getString(2));
        order.setCarName(rs.getString(3));
        order.setTotal(rs.getDouble(4));

        Date date = new Date((rs.getDate(5)).getTime());
        Date rentalStartDate = new Date((rs.getDate(6)).getTime());
        order.setDate(date);
        order.setRentalStartDate(rentalStartDate);


        order.setPeriod(rs.getInt(7));
        order.setRefusalReason(rs.getString(8));
        order.setDamages(rs.getString(9));
        order.setStatus(rs.getString(10));
        System.out.println(order);
        return order;
    }
    @Override
    public boolean specifyTheReasonForFailure(long id, String refusalReason) throws SQLException {
        LOG.info("OrderDAOImpl.specifyTheReasonForFailure()");

        psSpecifyTheReasonForFailure = prepareStatement(SET_REFUSAL_REASON);
        psSpecifyTheReasonForFailure.setString(1, refusalReason);
        psSpecifyTheReasonForFailure.setLong(2,(long)id);
        psSpecifyTheReasonForFailure.executeUpdate();
        return true;
    }

    @Override
    public boolean indicateDamage( long id, String damages) throws SQLException {
        LOG.info("OrderDAOImpl.indicateDamage()");

        psIndicateDamage = prepareStatement(SET_DAMAGES);
        psIndicateDamage.setString(1, damages);
        psIndicateDamage.setLong(2, (long)id);
        psIndicateDamage.executeUpdate();
        return true;
    }

    @Override
    public List<Order> getByUserId(long id) throws SQLException {
        LOG.info("OrderDAOImpl.getByUserId()");

        psGetByUserId = prepareStatement(GET_BY_USER_ID);
        psGetByUserId.setLong(1, (long)id);
        ResultSet rs = psGetByUserId.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrder(rs));
        }
        close(rs);
        return list;
    }


    @Override
    public List<Order> getAllNewOrders() throws SQLException {
        LOG.info("OrderDAOImpl.getAllNewOrders()");

        psGetAllNewOrders = prepareStatement(GET_ALL_NEW_ORDERS);
        ResultSet rs = psGetAllNewOrders.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrder(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public boolean changeOrderStatus(long id, String status) throws SQLException {
        LOG.info("OrderDAOImpl.changeOrderStatus()");

        psChangeOrderStatus = prepareStatement(CHANGE_ORDER_STATUS);
        psChangeOrderStatus.setString(1, status);
        psChangeOrderStatus.setLong(2, (long)id);
        psChangeOrderStatus.executeUpdate();
        return true;
    }

    @Override
    public Order save(Order order) throws SQLException {
        LOG.info("OrderDAOImpl.save()");

        psSave = prepareStatement(ADD_NEW_ORDER, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, order.getUserId());
        psSave.setLong(2, order.getCarId());
        psSave.setDouble(3, order.getTotal());

        java.sql.Date sql = new java.sql.Date(order.getRentalStartDate().getTime());
        psSave.setDate(4, sql);
        psSave.setInt(5, order.getPeriod());

            psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            order.setOrderId(rs.getInt(1));
        }
        close(rs);
        return order;
    }

    @Override
    public Order get(long id) throws SQLException {
        LOG.info("OrderDAOImpl.get()");

        psGet = prepareStatement(GET_ORDER_BY_ID);
        psGet.setLong(1,(long)id);
        ResultSet rs = psGet.executeQuery();

        if (rs.next()) {
            return populateOrder(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public int delete(long id) throws SQLException {
        LOG.info("OrderDAOImpl.delete()");

        psDelete = prepareStatement(DELETE_ORDER);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        LOG.info("OrderDAOImpl.getAll()");

        psGetAll = prepareStatement(GET_All_ORDERS);
        ResultSet rs = psGetAll.executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateOrder(rs));
        }
        close(rs);
        return list;
    }

    private Order populateOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getLong(1));
        order.setUserId(rs.getLong(2));
        order.setCarId(rs.getLong(3));
        order.setTotal(rs.getDouble(4));
        Date date = new Date((rs.getDate(5)).getTime());
        Date rentalStartDate = new Date((rs.getDate(6)).getTime());
        order.setDate(date);
        order.setRentalStartDate(rentalStartDate);
        order.setPeriod(rs.getInt(7));
        order.setRefusalReason(rs.getString(8));
        order.setDamages(rs.getString(9));
        order.setStatus(rs.getString(10));
        return order;
    }
}