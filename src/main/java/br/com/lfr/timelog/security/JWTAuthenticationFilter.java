package br.com.lfr.timelog.security;

import br.com.lfr.timelog.utils.PropertiesReader;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;


public class JWTAuthenticationFilter extends GenericFilterBean {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication((HttpServletRequest) request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) throws IOException {
        String secret = PropertiesReader.getProperty("jwt.secret");
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String userId = Jwts.parser()
                    .setSigningKey(Base64Utils.encodeToString(secret.getBytes()))
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody().get("uid", String.class);

            if (userId != null) {
                return new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
            }
        }
        return null;
    }

}
