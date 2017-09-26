package by.it_academy.web.command.impl.userController;

import by.it_academy.entities.User;
import by.it_academy.services.UserService;
import by.it_academy.services.impl.UserServiceImpl;
import by.it_academy.web.auth.Encoder;
import by.it_academy.web.auth.EnteredInfoValidator;
import by.it_academy.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterController implements Controller {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");
        String name = req.getParameter("realname");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

        req.getSession().setAttribute("login",login);
        req.getSession().setAttribute("realname", name);
        req.getSession().setAttribute("address", address);
        req.getSession().setAttribute("email", email);


        if (!password.equals(passwordRepeat)) {
            req.getSession().setAttribute("errorPassword", "1");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=register");
            return;
        }

        if (EnteredInfoValidator.validateRegistrationInfo(login, password)) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=register");
            return;
        }

        boolean flag = userService.checkLogin(login);
        if (flag) {
            req.getSession().setAttribute("errorLogin", 1);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/frontController?command=register");
            return;
        }
        password = Encoder.encode(password);
        User user = userService.create(name,password,address,email,login);
        user.setType("USER");
        req.getSession().setAttribute("user", user);
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath + "/frontController?command=car");
    }
}

