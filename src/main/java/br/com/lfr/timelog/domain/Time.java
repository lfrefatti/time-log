package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.InvalidPeriodException;
import br.com.lfr.timelog.exceptions.NullTimePropertyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Document(collection = "time")
public class Time {

    @Id
    private String id;
    private String project_id;
    private String user_id;
    private Date started_at;
    private Date ended_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getStarted_at() {
        return started_at;
    }

    public void setStarted_at(Date started_at) {
        this.started_at = started_at;
    }

    public Date getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(Date ended_at) {
        this.ended_at = ended_at;
    }

    public void validate(){
        if (project_id == null)
            throw new NullTimePropertyException("project_id");

        if (started_at == null)
            throw new NullTimePropertyException("started_at");

        if (ended_at == null)
            throw new NullTimePropertyException("ended_at");

        if (started_at.equals(ended_at) || started_at.after(ended_at))
            throw new InvalidPeriodException();
    }
}
