package by.it_academy.web.command.impl;


import by.it_academy.services.UserService;
import by.it_academy.services.impl.UserServiceImpl;
import by.it_academy.web.command.Controller;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckUserExistController implements Controller {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            boolean flag = userService.checkLogin(req.getParameter("login"));
            PrintWriter writer = resp.getWriter();
            if (flag) {
                writer.print(new Gson().toJson("Пользователь с таким логином существует"));
            } else {
                writer.print(new Gson().toJson("Логин свободен"));
            }
        }
    }
