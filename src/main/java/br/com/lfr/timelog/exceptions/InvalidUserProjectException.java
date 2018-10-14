package br.com.lfr.timelog.exceptions;

public class InvalidUserProjectException extends RuntimeException{

    public InvalidUserProjectException(String userId){
        super("User " + userId + " is not part of this project.");
    }

}
