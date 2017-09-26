package by.it_academy.web.filters;


import by.it_academy.entities.User;
import by.it_academy.web.command.enums.ControllerType;
import by.it_academy.web.handlers.RequestHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.it_academy.web.command.enums.ControllerType.*;


@WebFilter(urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        ControllerType type = RequestHandler.getCommand(req);
        if (!(ALL_CAR.equals(type)) && !(REGISTER.equals(type)) && !(REGISTRATION.equals(type)) &&
                !(LOGIN.equals(type)) && !(CHECK_USER_EXIST_AJAX.equals(type)) ) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            boolean flag = (session.getAttribute("user") == null);
            if(flag) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
            User user = (User)session.getAttribute("user");
            if(user.getType().equalsIgnoreCase("USER") && !(ORDERS.equals(type)) &&
                    !(ORDERING.equals(type)) && !(ORDERING_RE.equals(type)) && !(LOGOUT.equals(type))
                    && !(ERROR_AUTH.equals(type))){
                res.sendRedirect(contextPath + "/frontController?command=errorAuth");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}