package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullProjectPropertyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Document(collection = "project")
public class Project {

    @Id
    private String id;
    private String title;
    private String description;
    private List<String> user_id;
    private List<Time> times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<String> user_id) {
        this.user_id = user_id;
    }

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public void validate(){
        if (title == null)
            throw new NullProjectPropertyException("title");

        if (description == null)
            throw new NullProjectPropertyException("description");

        if (user_id == null || user_id.isEmpty())
            throw new NullProjectPropertyException("user_id");
    }

}
