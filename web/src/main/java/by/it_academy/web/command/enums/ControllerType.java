package by.it_academy.web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import by.it_academy.web.command.Controller;
import by.it_academy.web.command.impl.CheckUserExistController;
import by.it_academy.web.command.impl.ErrorAuthController;
import by.it_academy.web.command.impl.LoginController;
import by.it_academy.web.command.impl.LogoutController;
import by.it_academy.web.command.impl.adminController.*;
import by.it_academy.web.command.impl.userController.*;

@Getter
@AllArgsConstructor
public enum ControllerType {

    ALL_CAR("car/main.jsp", "Car", "car.title", new CarController()),
    LOGIN("login.jsp", "Login", "login.title", new LoginController()),
    LOGOUT("car/main.jsp", "Logout", "logout.title", new LogoutController()),


    REGISTER("register.jsp", "Register", "register.title" , new RegisterCommand()),
    REGISTRATION("", "Registration", "register.title", new RegisterController()),
    ORDERS("orders/main.jsp", "Orders", "orders.title", new OrderController()),
    ORDERING("user/ordering.jsp", "Ordering", "", new OrderingController()),
    ORDERING_RE("user/ordering.jsp", "OrderingRe", "ordering.title", new OrderingReController()),
    ORDERS_NEW("orders/main.jsp", "newOrders", "orders.title", new OrderNewController()),
    ORDERS_ALL("orders/main.jsp", "allOrders", "orders.title", new OrderAllController()),
    CHANGE_ORDER_STATUS("orders/main.jsp", "changeOrderStatus", "orders.title", new ChangeOrderStatusController()),
    INDICATE_DAMAGE("orders/main.jsp", "indicateDamage", "orders.title" , new IndicateDamageController()),
    CHECK_USER_EXIST_AJAX("","checkUserExistController", "",new CheckUserExistController()),
    ERROR_AUTH("erroraut.jsp", "errorAuth", "error.title", new ErrorAuthController()),


    DELETE_USER("admin/users.jsp", "delete", "admin.active.users", new DeleteUserController()),


    ALL_ACTIVE_USERS("admin/users.jsp", "activeUsers", "admin.active.users", new UsersActiveController());





    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return ALL_CAR;
    }
}
