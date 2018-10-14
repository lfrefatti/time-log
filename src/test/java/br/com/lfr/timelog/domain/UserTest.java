package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullUserPropertyException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private User user;

    @Before
    public void setUp(){
        user = new User();
        user.setName("Name");
        user.setEmail("user@mail.com");
        user.setLogin("user");
        user.setPassword("password");
    }

    @Test public void
    should_throw_Exception_when_validate_user_with_null_name(){
        expectedException.expect(NullUserPropertyException.class);
        user.setName(null);
        user.validate();
    }

    @Test public void
    should_throw_Exception_when_validate_user_with_null_email(){
        expectedException.expect(NullUserPropertyException.class);
        user.setEmail(null);
        user.validate();
    }

    @Test public void
    should_throw_Exception_when_validate_user_with_null_login(){
        expectedException.expect(NullUserPropertyException.class);
        user.setLogin(null);
        user.validate();
    }

    @Test public void
    should_thrwoException_when_validade_user_with_null_password(){
        expectedException.expect(NullUserPropertyException.class);
        user.setPassword(null);
        user.validate();
    }

}