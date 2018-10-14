package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.domain.User;
import br.com.lfr.timelog.exceptions.DuplicatedLoginException;
import br.com.lfr.timelog.exceptions.UserNotFoundException;
import br.com.lfr.timelog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.rules.ExpectedException.none;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl service;

    @Rule
    public ExpectedException expectedException = none();

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test public void
    should_throw_Exceptions_when_tries_to_find_not_existing_user(){
        expectedException.expect(UserNotFoundException.class);
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        service.findUserById("");
    }

    @Test public void
    should_throw_exception_when_tries_to_insert_already_existing_logind(){
        expectedException.expect(DuplicatedLoginException.class);
        when(userRepository.findOne(any(Example.class))).thenReturn(Optional.of(new User()));
        service.validateLogin("");
    }

}