package by.it_academy.web.handlers;


import by.it_academy.web.command.enums.ControllerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.it_academy.web.command.enums.ControllerType.ALL_CAR;


public class RequestHandler {
    public static ControllerType getCommand(HttpServletRequest req){
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "car.title";
        }


        ControllerType type = ControllerType.getByPageName(param);
        req.setAttribute("title", type.getI18nKey());
        HttpSession session = req.getSession();
        String pageName = (String)session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", ALL_CAR.getPageName());
            session.setAttribute("pagePath", ALL_CAR.getPagePath());
        }

        return type;
    }
}
