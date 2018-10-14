package br.com.lfr.timelog.domain;

public class AuthWrapper {

    private String token;
    private User user;

    public AuthWrapper(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

}
