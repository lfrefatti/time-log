package br.com.lfr.timelog.interfaces;

import br.com.lfr.timelog.domain.Project;
import br.com.lfr.timelog.domain.ProjectWrapper;
import br.com.lfr.timelog.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public @ResponseBody ProjectWrapper post(@RequestBody Project project){
        return new ProjectWrapper(projectService.insertProject(project));
    }

    @RequestMapping(path = "/{id}", method = GET)
    @ResponseStatus(OK)
    public @ResponseBody ProjectWrapper get(@PathVariable String id){
        return new ProjectWrapper(projectService.findProjectById(id));
    }

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public @ResponseBody List<Project> get(){
        return projectService.findProjects();
    }

    @RequestMapping(path = "/{id}", method = PUT)
    @ResponseStatus(NO_CONTENT)
    public void put(@RequestBody Project project, @PathVariable String id){
        projectService.updateProject(project, id);
    }

}
