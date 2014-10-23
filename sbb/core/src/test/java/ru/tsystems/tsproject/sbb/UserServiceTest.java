package ru.tsystems.tsproject.sbb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tsystems.tsproject.sbb.services.UserService;
import ru.tsystems.tsproject.sbb.transferObjects.UserTO;

/**
 * Created by apple on 23.10.14.
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void initUserServiceTest() {
        userService = new UserService();
    }

    @Test
    public void testRegisterUser_Added() throws Exception {
        String name = "Jane";
        String surname = "Air";
        String birthDate = "21.05.1987";
        String login = "jane";
        String password = "jane";
        userService.registerUser(name, surname, birthDate, login, password);
        UserTO user = userService.getUser(login);
        Assert.assertEquals(name, user.getName());
        Assert.assertEquals(surname, user.getSurname());
        Assert.assertEquals(birthDate, user.getBirthDate());
        Assert.assertEquals(login, user.getLogin());
        Assert.assertEquals(password, user.getPassword());
    }
}
