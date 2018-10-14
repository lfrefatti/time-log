package br.com.lfr.timelog.exceptions;

public class InvalidLoginException extends RuntimeException{

    public InvalidLoginException(String login){
        super("Invalid login: " + login);
    }

}
