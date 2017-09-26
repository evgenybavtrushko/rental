package by.it_academy;

import by.it_academy.entities.User;
import by.it_academy.services.UserService;
import by.it_academy.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest {
    private UserService userService = UserServiceImpl.getInstance();

    @Test
    public void testGetByLogin(){
        User user = userService.getByLogin("nimf");
        String type = user.getType();
        assertEquals(type, "ADMIN");
    }

}
