package br.com.lfr.timelog.exceptions;

public class TimeNotFoundException extends RuntimeException{

    public TimeNotFoundException(String id){
        super("Time " + id + " not found.");
    }
}
