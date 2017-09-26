package by.it_academy.web.command.impl;

import by.it_academy.services.CarService;
import by.it_academy.services.impl.CarServiceImpl;
import by.it_academy.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController implements Controller {
    private CarService carService = CarServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getSession().setAttribute("carForUser", carService.getAllActiveCar());
        req.getServletContext().getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
