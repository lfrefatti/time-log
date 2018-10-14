package br.com.lfr.timelog.interfaces;

import br.com.lfr.timelog.domain.AuthWrapper;
import br.com.lfr.timelog.domain.Login;
import br.com.lfr.timelog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(method = POST)
    @ResponseStatus(OK)
    public @ResponseBody AuthWrapper authenticate(@RequestBody Login login) throws UnsupportedEncodingException {
        return authService.authenticate(login);
    }

}
