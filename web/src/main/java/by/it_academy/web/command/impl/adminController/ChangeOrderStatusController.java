package by.it_academy.web.command.impl.adminController;

import by.it_academy.services.OrderService;
import by.it_academy.services.impl.OrderServiceImpl;
import by.it_academy.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeOrderStatusController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long orderId = Long.parseLong(req.getParameter("orderId"));

        if (req.getParameter("confirm").equals("confirm")) {
            orderService.changeOrderStatus(orderId, "APPROVED_BY");


        } else if (req.getParameter("confirm").equals("refuse")) {
            orderService.changeOrderStatus(orderId, "DENIED");
            orderService.specifyTheReasonForFailure(orderId, req.getParameter("reasonForRefusal"));

        } else if (req.getParameter("confirm").equals("archive")){
            orderService.changeOrderStatus(orderId, "ARCHIVE");
        }
        req.getSession().setAttribute("orders", orderService.getAllDTO());
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}

