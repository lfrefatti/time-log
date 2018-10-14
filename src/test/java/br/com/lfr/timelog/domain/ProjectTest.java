package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullProjectPropertyException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.rules.ExpectedException.none;

public class ProjectTest {

    @Rule
    public ExpectedException expectedException = none();

    private Project project;

    @Before
    public void setUp(){
        project = new Project();
        project.setTitle("title");
        project.setDescription("desc");
        project.setUser_id((Arrays.asList("123456")));
    }

    @Test
    public void
    should_throw_exception_when_validate_project_with_null_title(){
        expectedException.expect(NullProjectPropertyException.class);
        project.setTitle(null);
        project.validate();
    }

    @Test
    public void
    should_throw_exception_when_validate_project_with_null_description(){
        expectedException.expect(NullProjectPropertyException.class);
        project.setDescription(null);
        project.validate();
    }

    @Test
    public void
    should_throw_exception_when_validate_project_with_null_user_id(){
        expectedException.expect(NullProjectPropertyException.class);
        project.setUser_id(null);
        project.validate();
    }

    @Test
    public void
    should_throw_exception_when_validate_project_with_empty_user_id_list(){
        expectedException.expect(NullProjectPropertyException.class);
        project.setUser_id(new ArrayList<>());
        project.validate();
    }

}