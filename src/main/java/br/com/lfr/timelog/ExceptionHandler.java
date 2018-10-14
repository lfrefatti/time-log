package br.com.lfr.timelog;

import br.com.lfr.timelog.domain.MessageWrapper;
import br.com.lfr.timelog.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public @ResponseBody
    MessageWrapper handleUserNotFoundException(UserNotFoundException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicatedLoginException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleDuplicatedLoginException(DuplicatedLoginException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullUserPropertyException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleNullUserPropertyException(NullUserPropertyException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullLoginPropertyException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleNullLoginPropertyException(NullLoginPropertyException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(UNAUTHORIZED)
    public @ResponseBody MessageWrapper handleInvalidLoginException(InvalidLoginException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(UNAUTHORIZED)
    public @ResponseBody MessageWrapper handleInvalidPasswordException(InvalidPasswordException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullProjectPropertyException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleNullProjectPropertyException(NullProjectPropertyException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPeriodException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleInvalidPeriodException(InvalidPeriodException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidUserProjectException.class)
    @ResponseStatus(BAD_REQUEST)
    public @ResponseBody MessageWrapper handleInvalidUserProjectException(InvalidUserProjectException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TimeNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public @ResponseBody MessageWrapper handleTimeNotFoundException(TimeNotFoundException e){
        LOGGER.warn(e.getMessage(), e);
        return new MessageWrapper(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public @ResponseBody MessageWrapper handleException(Exception e){
        LOGGER.error(e.getMessage(), e);
        return new MessageWrapper("Internal server error. " + e.getMessage());
    }

}
