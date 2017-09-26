package by.it_academy.services;

import by.it_academy.entities.Order;
import by.it_academy.dto.OrderDTO;

import java.util.Date;
import java.util.List;

public interface OrderService {
    boolean specifyTheReasonForFailure(long id, String refusalReason);
    boolean indicateDamage(long id, String damages);
    List<Order> getByUserId(long id);
    List<Order> getAllNewOrders();
    boolean changeOrderStatus(long id, String status);
    Order create(long userId, long carId, Date rental_start_date, int period);
    Order get(long id);
    int delete(long id);
    List<Order> getAll();

    List<OrderDTO> getAllDTO();
    List<OrderDTO> getAllNewOrdersDTO();
    List<OrderDTO> getByUserIdDTO(long id);
}
