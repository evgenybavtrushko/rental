package by.it_academy.web.command.impl;


import by.it_academy.entities.User;
import by.it_academy.services.UserService;
import by.it_academy.services.impl.UserServiceImpl;
import by.it_academy.web.auth.Encoder;
import by.it_academy.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginController implements Controller {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        /*int s = 2- 2;
        int d = 4/s;*/
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
            return;
        }
        User user = userService.getByLogin(login);
        if (user != null && user.getPassword().equals(Encoder.encode(password))) {
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            if(user.getType().equalsIgnoreCase("USER")) {
                resp.sendRedirect(contextPath + "/frontController?command=orders");
                return;
            }
            else if(user.getType().equalsIgnoreCase("ADMIN")){
                resp.sendRedirect(contextPath + "/frontController?command=newOrders");
                return;
            }
            return;
        } else {
            resp.setHeader("errorMsg", "Invalid Login or Password");
            req.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(req, resp);
           return;
        }
    }
}

