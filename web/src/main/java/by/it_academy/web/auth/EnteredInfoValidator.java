package by.it_academy.web.auth;

import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class EnteredInfoValidator {

    private static final Logger LOG = Logger.getLogger(EnteredInfoValidator.class);

    public static final String LOGIN_REGEX = "^[a-z0-9]{3,16}$";
    public static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    public static final String PASSWORD_REGEX = "^[a-z0-9_-]{6,16}$";
    public static final int MIN_PERIOD = 1;
    public static final int MAX_PERIOD = 30;


    public static boolean validateRegistrationInfo(String login, String password) {
        LOG.debug("total val: " + (loginVal(login) | passwordVal(password)));
        return loginVal(login) | passwordVal(password);
    }


    public static boolean loginVal(String login) {
        LOG.debug("loginVal" + !Pattern.matches(LOGIN_REGEX, login));
        return !Pattern.matches(LOGIN_REGEX, login);
    }

    public static boolean passwordVal(String password) {
        LOG.debug("passwordVal" + !Pattern.matches(PASSWORD_REGEX, password));
        return !Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean periodVal(int period) {
        return (period >= MIN_PERIOD) || (period <= MAX_PERIOD);
    }
}
