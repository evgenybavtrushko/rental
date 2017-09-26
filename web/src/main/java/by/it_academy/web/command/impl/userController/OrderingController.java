package by.it_academy.web.command.impl.userController;


import by.it_academy.entities.Car;
import by.it_academy.entities.Order;
import by.it_academy.entities.User;
import by.it_academy.services.OrderService;
import by.it_academy.services.impl.OrderServiceImpl;
import by.it_academy.web.auth.EnteredInfoValidator;
import by.it_academy.web.command.Controller;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderingController implements Controller {
    private static final Logger LOG = Logger.getLogger(OrderingController.class);
    OrderService orderService = OrderServiceImpl.getInstance();
    User user;
    Car car;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        user = (User) req.getSession().getAttribute("user");
        car = (Car) req.getSession().getAttribute("car");

        long userId = user.getId();
        long carId = car.getCarId();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(req.getParameter("date"));
        } catch (ParseException ex) {
            LOG.error("ParseException while converting date: " + ex);
        }
        int period = Integer.parseInt(req.getParameter("period"));
        if (!EnteredInfoValidator.periodVal(period)) {
            req.setAttribute("cpError", 1);
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
        Order order = orderService.create(userId, carId, date, period);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/frontController?command=car");
    }
}

