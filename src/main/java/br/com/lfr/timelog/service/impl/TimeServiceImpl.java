package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.exceptions.InvalidUserProjectException;
import br.com.lfr.timelog.exceptions.TimeNotFoundException;
import br.com.lfr.timelog.repositories.TimeRepository;
import br.com.lfr.timelog.domain.Project;
import br.com.lfr.timelog.domain.Time;
import br.com.lfr.timelog.service.ProjectService;
import br.com.lfr.timelog.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeServiceImpl implements TimeService {

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    ProjectService projectService;

    public Time insertTime(Time time, String userId) {
        time.validate();
        time.setUser_id(userId);
        validateProject(time.getProject_id(), userId);
        return timeRepository.insert(time);
    }

    public List<Time> findProjectTimes(String projectId) {
        Time exampleTime = new Time();
        exampleTime.setProject_id(projectId);
        return timeRepository.findAll(Example.of(exampleTime));
    }

    public void updateTime(Time time, String userId, String timeId) {
        time.validate();
        time.setUser_id(userId);
        validateProject(time.getProject_id(), userId);
        findTimeById(timeId);
        time.setId(timeId);
        timeRepository.save(time);
    }

    public Time findTimeById(String id) {
        Optional<Time> time = timeRepository.findById(id);

        if (time.isPresent())
            return time.get();

        throw new TimeNotFoundException(id);
    }

    protected void validateProject(String projectId, String userId) {
        Project project = projectService.findProjectById(projectId);
        Optional<String> id = project.getUser_id().stream().filter(uid -> uid.equals(userId)).findFirst();

        if (!id.isPresent())
            throw new InvalidUserProjectException(userId);
    }


}
