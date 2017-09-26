package by.it_academy.dao;

import by.it_academy.entities.Order;
import by.it_academy.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends DAO<Order> {

    boolean specifyTheReasonForFailure(long id, String refusalReason) throws SQLException;

    boolean indicateDamage(long id, String damages) throws SQLException;

    List<Order> getByUserId(long id) throws SQLException;

    List<Order> getAllNewOrders() throws SQLException;

    boolean changeOrderStatus(long id, String status) throws SQLException;

    List<OrderDTO> getAllDTO() throws SQLException;

    List<OrderDTO> getByUserIdDTO(long id) throws SQLException;

    List<OrderDTO> getAllNewOrdersDTO() throws SQLException;



}
