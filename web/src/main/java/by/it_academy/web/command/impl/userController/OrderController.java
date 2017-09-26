package by.it_academy.web.command.impl.userController;


import by.it_academy.entities.User;
import by.it_academy.dto.OrderDTO;
import by.it_academy.services.OrderService;
import by.it_academy.services.impl.OrderServiceImpl;
import by.it_academy.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class OrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<OrderDTO> orders = orderService.getByUserIdDTO(user.getId());

        req.setAttribute("orders", orders);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
