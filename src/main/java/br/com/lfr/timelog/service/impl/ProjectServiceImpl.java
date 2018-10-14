package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.exceptions.ProjectNotFoundException;
import br.com.lfr.timelog.repositories.ProjectRepository;
import br.com.lfr.timelog.domain.Project;
import br.com.lfr.timelog.domain.Time;
import br.com.lfr.timelog.service.ProjectService;
import br.com.lfr.timelog.service.TimeService;
import br.com.lfr.timelog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TimeService timeService;

    @Autowired
    UserService userService;

    public Project findProjectById(String projectId) {
        Optional<Project> opProject = projectRepository.findById(projectId);

        if (!opProject.isPresent())
            throw new ProjectNotFoundException(projectId);

        Project project = opProject.get();
        List<Time> projectTimes = timeService.findProjectTimes(projectId);
        project.setTimes(projectTimes);

        return project;
    }

    public Project insertProject(Project project) {
        project.validate();
        validateUsers(project.getUser_id());
        project.setTimes(null);
        return projectRepository.insert(project);
    }

    public void updateProject(Project projectToUpdate, String projectId) {
        projectToUpdate.validate();
        validateUsers(projectToUpdate.getUser_id());
        Project project = findProjectById(projectId);
        projectToUpdate.setId(project.getId());
        projectToUpdate.setTimes(null);
        projectRepository.save(projectToUpdate);
    }

    public List<Project> findProjects() {
        return projectRepository.findAll();
    }

    private void validateUsers(List<String> user_id) {
        user_id.stream().forEach(uid -> userService.findUserById(uid));
    }
}
