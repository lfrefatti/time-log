package br.com.lfr.timelog.domain;

import br.com.lfr.timelog.exceptions.NullLoginPropertyException;

public class Login {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validate(){
        if (login == null)
            throw new NullLoginPropertyException("login");

        if (password == null){
            throw new NullLoginPropertyException("password");
        }
    }
}
