package br.com.lfr.timelog.service;

import br.com.lfr.timelog.domain.AuthWrapper;
import br.com.lfr.timelog.domain.Login;

import java.io.UnsupportedEncodingException;

public interface AuthService {

    AuthWrapper authenticate(Login login) throws UnsupportedEncodingException;

}
