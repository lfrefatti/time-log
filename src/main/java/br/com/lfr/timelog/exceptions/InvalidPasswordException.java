package br.com.lfr.timelog.exceptions;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String login){
        super("Invalid password for user " + login);
    }

}
