package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.domain.Login;
import br.com.lfr.timelog.domain.User;
import br.com.lfr.timelog.exceptions.InvalidLoginException;
import br.com.lfr.timelog.exceptions.InvalidPasswordException;
import br.com.lfr.timelog.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.rules.ExpectedException.none;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthServiceImplTest {

    @Rule
    public ExpectedException expectedException = none();

    @Mock
    UserService userService;

    @InjectMocks
    AuthServiceImpl authService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test public void
    should_throw_exception_when_user_is_not_found(){
        expectedException.expect(InvalidLoginException.class);
        when(userService.findUserByLogin(anyString())).thenReturn(Optional.empty());
        authService.login(new Login());
    }

    @Test public void should_throw_exception_when_passwords_does_not_matches(){
        expectedException.expect(InvalidPasswordException.class);

        User user = new User();
        user.setPassword("1234");
        user.encodePassword();
        Optional<User> optionalUser = Optional.of(user);
        when(userService.findUserByLogin(anyString())).thenReturn(optionalUser);

        Login login = new Login();
        login.setLogin("user");
        login.setPassword("789");

        authService.login(login);
    }

}