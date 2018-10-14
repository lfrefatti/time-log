package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.InvalidPeriodException;
import br.com.lfr.timelog.exceptions.NullTimePropertyException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;

import static org.junit.rules.ExpectedException.none;

public class TimeTest {

    @Rule
    public ExpectedException expectedException = none();

    private Time time;

    @Before
    public void setUp(){
        time = new Time();
        time.setProject_id("");
        time.setStarted_at(new Date());
        time.setEnded_at(new Date());
    }

    @Test public void
    should_throw_exception_when_validate_time_with_null_project_id(){
        expectedException.expect(NullTimePropertyException.class);
        time.setProject_id(null);
        time.validate();
    }

    @Test public void
    should_throw_exception_when_validate_time_with_null_started_at(){
        expectedException.expect(NullTimePropertyException.class);
        time.setStarted_at(null);
        time.validate();
    }

    @Test public void
    should_throw_exception_when_validate_time_with_null_ended_at(){
        expectedException.expect(NullTimePropertyException.class);
        time.setEnded_at(null);
        time.validate();
    }

    @Test public void
    should_throw_exception_when_start_date_is_after_end_date() throws InterruptedException {
        expectedException.expect(InvalidPeriodException.class);
        Thread.sleep(100);
        time.setStarted_at(new Date());
        time.validate();
    }

    @Test public void
    should_throw_exception_when_start_date_is_equals_end_date(){
        expectedException.expect(InvalidPeriodException.class);
        time.setStarted_at(time.getEnded_at());
        time.validate();
    }

}