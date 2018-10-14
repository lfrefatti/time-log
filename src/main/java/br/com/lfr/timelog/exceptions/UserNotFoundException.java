package br.com.lfr.timelog.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String userId){
        super("User " + userId + " not found.");
    }

}
