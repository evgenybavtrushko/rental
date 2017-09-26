package by.it_academy.impl;

import by.it_academy.dao.UserDao;
import by.it_academy.dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoImplTest {

    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String login = "";
        UserDao instance = UserDaoImpl.getInstance();
        boolean expResult = false;
        boolean result = instance.checkLogin(login);
        assertEquals(expResult, result);
    }


   /* @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        long userId = 2;
        UserDao instance = UserDaoImpl.getInstance();
        long expResult = 1;
        int result = instance.delete(userId);
        assertEquals(expResult, result);
    }*/

    @Test
    public void testOldApi() {
//        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 8);
//        calendar.add(Calendar.DAY_OF_MONTH, 2);

        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("EEEEE d MM yyyy");
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEE dd M yyyy");
        try {
            Date date = sdf.parse(format);
            System.out.println(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
