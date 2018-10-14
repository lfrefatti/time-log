package br.com.lfr.timelog.exceptions;

public class InvalidPeriodException extends RuntimeException {

    public InvalidPeriodException(){
        super("started_at must be before ended_at.");
    }

}
