package dev.hugodesouzacaramez.layered.service;

import dev.hugodesouzacaramez.layered.service.dto.UserDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UserServiceTest {

    @Inject
    UserService userService;

    @Test
    public void givenTheUserEmailAlreadyExistsAnExceptionIsThrown() throws Exception {
        var userDto = new UserDto("test@hugodesouzacaramez.dev", "password");
        userService.createAccount(userDto);
        Assertions.assertThrows(
                Exception.class,
                ()-> userService.createAccount(userDto)
        );
    }

    @Test
    public void giveThePasswordIsCorrectTheAuthenticationPass() throws Exception {
        var userDto = new UserDto("test2@hugodesouzacaramez.dev", "password");
        userService.createAccount(userDto);
        var loginResult = userService.login(userDto);
        Assertions.assertEquals(loginResult, "Authenticated with success");
    }

    @Test
    public void giveThePasswordIsNotCorrectTheAuthenticationFail() throws Exception {
        var userDto = new UserDto("test3@hugodesouzacaramez.dev", "password");
        userService.createAccount(userDto);
        var userDtoWrongPass = new UserDto("test3@hugodesouzacaramez.dev", "wrongPassword");
        var loginResult = userService.login(userDtoWrongPass);
        Assertions.assertEquals(loginResult, "Invalid credentials");
    }
}