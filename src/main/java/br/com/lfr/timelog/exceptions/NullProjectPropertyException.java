package br.com.lfr.timelog.exceptions;

public class NullProjectPropertyException extends RuntimeException {

    public NullProjectPropertyException(String property){
        super(property + " is required or must not be empty.");
    }

}
