package br.com.lfr.timelog.service;

import br.com.lfr.timelog.domain.Project;

import java.util.List;

public interface ProjectService {

    Project findProjectById(String projectId);

    Project insertProject(Project project);

    void updateProject(Project project, String projectId);

    List<Project> findProjects();
}
