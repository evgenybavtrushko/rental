package by.it_academy.web.command.impl.userController;

import by.it_academy.services.CarService;
import by.it_academy.services.impl.CarServiceImpl;
import by.it_academy.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CarController implements Controller {
    private CarService carService = CarServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("carForUser", carService.getAllActiveCar());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
