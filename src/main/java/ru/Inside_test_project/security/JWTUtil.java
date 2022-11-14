package ru.Inside_test_project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JWTUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    /**
     * @param userDetails
     * @return generated token
     */
    // генерация токена (кладем в него имя пользователя и authorities)
    public String generateToken(UserDetails userDetails) {
        log.info("generateToken");
        Map<String, Object> claims = new HashMap<>();
        String commaSeparatedListOfAuthorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        claims.put("authorities", commaSeparatedListOfAuthorities);
        return createToken(claims, userDetails.getUsername());
    }

    public String extractUsername(String token) {
        log.info("extract Username from token");
        return extractClaim(token, Claims::getSubject);
    }

    public String extractAuthorities(String token) {
        log.info("extract Authorities from token");
        Function<Claims, String> claimsListFunction = claims -> (String) claims.get("authorities");
        return extractClaim(token, claimsListFunction);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        log.info("extract Claim from token");
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        log.info("extract all Clims from token");
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    private String createToken(Map<String, Object> claims, String subject) {
        log.info("Create Token");
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                //exp — когда истекает токен
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //iat -когда выпущен токен
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
