package by.it_academy.web.command.impl.userController;


import by.it_academy.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);

    }
}
