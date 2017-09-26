
package by.it_academy.entity;

import by.it_academy.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User instance = new User();
    public UserTest() {
    }
    @Test
    @BeforeEach
    public void testSetEmail() throws Exception {
        System.out.println("setEmail");
        String email = "e-mail";
        instance.setEmail(email);
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "e-mail";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }



    @Test
    public void testGetName() {
        System.out.println("getName");

        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    @BeforeEach
    public void testSetName() throws Exception {
        System.out.println("setName");
        String name = "name";

        instance.setName(name);
    }


    @Test
    public void testGetType() {
        System.out.println("getType");

        String expResult = "USER";
        String result = instance.getType();
        assertEquals(expResult, result);
    }
    @Test
    @BeforeEach
    public void testSetType() {
        System.out.println("setType");
        String s = "USER";

        instance.setType(s);
    }


    @Test
    public void testGetLogin() {
        System.out.println("getLogin");

        String expResult = "login";
        String result = instance.getLogin();
        assertEquals(expResult, result);
    }

    @Test
    @BeforeEach
    public void testSetLogin() throws Exception {
        System.out.println("setLogin");
        String login = "login";

        instance.setLogin(login);
    }


    @Test
    public void testGetPassword() {
        System.out.println("getPassword");

        String expResult = "passNum";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    @BeforeEach
    public void testSetPassword() throws Exception {
        System.out.println("setPassword");
        String password = "passNum";
        instance.setPassword(password);
    }

}
