package com.mozdal.jwt.example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "secretTokenMO";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String AUTH_HEADER_STRING = "Authorization";
    private static final String EMAIL_KEY_STRING = "Email";
    private static final String USERNAME_KEY_STRING = "Username";
    private static final String AUTHORITIES_KEY_STRING = "Authority";

    static void addAuthentication(HttpServletResponse res, String email, String username, String authorityName) {
        //Create payload
        JSONObject payload = new JSONObject();
        payload.put(EMAIL_KEY_STRING, email);
        payload.put(USERNAME_KEY_STRING, username);
        payload.put(AUTHORITIES_KEY_STRING, authorityName);
        payload.put("exp", System.currentTimeMillis() + EXPIRATIONTIME);
        payload.put("iat", System.currentTimeMillis());
        payload.put("sub", email);
        String JWT = Jwts.builder()
                .setPayload(payload.toString())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        res.addHeader(AUTH_HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        //For enabling testing from localhost
        res.addHeader("Access-Control-Allow-Origin","http://localhost:4200");
        //For exposing JWT Token in Header
        res.addHeader("Access-Control-Expose-Headers","Authorization");
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().get("Email").toString();

            return user != null ?  new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }
}
