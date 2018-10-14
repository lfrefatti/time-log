package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.domain.Project;
import br.com.lfr.timelog.exceptions.InvalidUserProjectException;
import br.com.lfr.timelog.exceptions.TimeNotFoundException;
import br.com.lfr.timelog.repositories.TimeRepository;
import br.com.lfr.timelog.service.ProjectService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.rules.ExpectedException.none;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TimeServiceImplTest {

    @Rule
    public ExpectedException expectedException = none();

    @Mock
    TimeRepository timeRepository;

    @Mock
    ProjectService projectService;

    @InjectMocks
    TimeServiceImpl timeService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test public void
    should_throw_exception_when_user_is_not_present_on_project(){
        expectedException.expect(InvalidUserProjectException.class);
        Project project = new Project();
        project.setUser_id(new ArrayList<String>());
        when(projectService.findProjectById(anyString())).thenReturn(project);
        timeService.validateProject("", "id");
    }

    @Test public void
    should_throw_exception_when_try_to_find_unexisting_time(){
        expectedException.expect(TimeNotFoundException.class);
        when(timeRepository.findById(anyString())).thenReturn(Optional.empty());
        timeService.findTimeById("");
    }

}