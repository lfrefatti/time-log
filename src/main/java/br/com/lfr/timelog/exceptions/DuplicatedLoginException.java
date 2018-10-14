package br.com.lfr.timelog.exceptions;

public class DuplicatedLoginException extends RuntimeException {

    public DuplicatedLoginException(String login){
        super("The login " + login + " already exists.");
    }

}
