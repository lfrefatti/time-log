package br.com.lfr.timelog.exceptions;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(String projectId){
        super("Project " + projectId + " not found.");
    }

}
