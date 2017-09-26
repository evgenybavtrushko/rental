package by.it_academy.web.command.impl.userController;

import by.it_academy.entities.Car;
import by.it_academy.services.CarService;
import by.it_academy.services.impl.CarServiceImpl;
import by.it_academy.web.command.Controller;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderingReController implements Controller {
    private CarService carService = CarServiceImpl.getInstance();
    private static final Logger LOG = Logger.getLogger(OrderingReController.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long carId = 0;
        if (req.getParameter("carId") != null) {
            carId = Long.parseLong(req.getParameter("carId"));
            req.getSession().setAttribute("carId", carId);
        }else {
            carId = (long) req.getSession().getAttribute("carId");
        }
        Car car = carService.get(carId);
        req.getSession().setAttribute("car", car);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}