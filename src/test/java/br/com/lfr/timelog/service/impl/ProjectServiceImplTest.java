package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.exceptions.ProjectNotFoundException;
import br.com.lfr.timelog.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.rules.ExpectedException.none;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Rule
    public ExpectedException expectedException = none();

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test public void
    should_throw_exception_when_tries_to_find_not_existing_project(){
        expectedException.expect(ProjectNotFoundException.class);
        when(projectRepository.findById(anyString())).thenReturn(Optional.empty());
        projectService.findProjectById("");
    }

}