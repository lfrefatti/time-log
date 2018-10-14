package br.com.lfr.timelog.exceptions;

public class NullUserPropertyException extends RuntimeException{

    public NullUserPropertyException(String property){
        super(property + " value must not be null.");
    }

}
