package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullUserPropertyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String login;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validate(){
        if (this.name == null)
            throw new NullUserPropertyException("name");

        if (this.email == null)
            throw new NullUserPropertyException("email");

        if (this.login == null)
            throw new NullUserPropertyException("login");

        if (this.password == null)
            throw new NullUserPropertyException("password");
    }

    public void encodePassword(){
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }

}
