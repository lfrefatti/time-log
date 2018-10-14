package br.com.lfr.timelog.exceptions;

public class NullTimePropertyException extends RuntimeException{

    public NullTimePropertyException(String property){
        super(property + " value must not be null.");
    }

}
