package br.com.lfr.timelog.exceptions;

public class NullLoginPropertyException extends RuntimeException{

    public NullLoginPropertyException(String property){
        super("Missing required " + property + " property.");
    }

}
