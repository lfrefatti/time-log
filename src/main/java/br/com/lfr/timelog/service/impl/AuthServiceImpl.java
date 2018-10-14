package br.com.lfr.timelog.service.impl;

import br.com.lfr.timelog.exceptions.InvalidLoginException;
import br.com.lfr.timelog.exceptions.InvalidPasswordException;
import br.com.lfr.timelog.service.AuthService;
import br.com.lfr.timelog.service.UserService;
import br.com.lfr.timelog.domain.AuthWrapper;
import br.com.lfr.timelog.domain.Login;
import br.com.lfr.timelog.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    private static final Integer timeDelta = Integer.valueOf(24);

    public AuthWrapper authenticate(Login login) throws UnsupportedEncodingException {
        login.validate();
        User user = login(login);
        String token = generateToken(user.getId());
        return new AuthWrapper(user, token);
    }

    protected User login(Login login){
        Optional<User> user = userService.findUserByLogin(login.getLogin());

        if (!user.isPresent())
            throw new InvalidLoginException(login.getLogin());

        if (!BCrypt.checkpw(login.getPassword(), user.get().getPassword()))
            throw new InvalidPasswordException(login.getLogin());

        return user.get();
    }

    private String generateToken(String userId) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setExpiration(getExpTimestamp())
                .claim("uid", userId)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                .compact();
    }

    private Date getExpTimestamp(){
        LocalDateTime exp = LocalDateTime.now().plusHours(timeDelta);
        return Date.from(exp.atZone(ZoneId.systemDefault()).toInstant());
    }
}
