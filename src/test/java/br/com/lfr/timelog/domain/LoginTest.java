package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullLoginPropertyException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.rules.ExpectedException.none;

public class LoginTest {

    @Rule
    public ExpectedException expectedException = none();

    private Login login;

    @Before
    public void setUp(){
        login = new Login();
        login.setLogin("user");
        login.setPassword("password");
    }

    @Test public void
    should_throw_exception_when_validate_login_with_null_login(){
        expectedException.expect(NullLoginPropertyException.class);
        login.setLogin(null);
        login.validate();
    }

    @Test public void
    should_throw_exception_when_validate_login_with_null_password(){
        expectedException.expect(NullLoginPropertyException.class);
        login.setPassword(null);
        login.validate();
    }

}