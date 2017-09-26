package by.it_academy.web.command.impl.adminController;

import by.it_academy.services.UserService;
import by.it_academy.services.impl.UserServiceImpl;
import by.it_academy.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        long userId = Long.parseLong(req.getParameter("userId"));
        userService.delete(userId);
        req.getSession().setAttribute("activeUser", userService.getAllActiveUsers());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
