package com.example.demo.configurations;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final Key key;
    private final UserRepository userRepository;

    public JwtTokenUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
        // random string, maybe change in future with more elegant random
        String secret = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public static final long JWT_TOKEN_VALIDITY = 480 * 60 * 60;

    public String getUsername(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("username", String.class);
    }

    public Tuple2<Long, String> getUserIdAndRoleFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        Long id = claims.get("id", Long.class);
        String role = claims.get("role", String.class);
        return Tuples.of(id, role);
    }

    public List<String> getRolesListFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        String role = claims.get("role", String.class);
        return Collections.singletonList(role);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with name cannot be found"));
        claims.put("id", user.getId());
        claims.put("username", userDetails.getUsername());
        claims.put("role", userDetails.getRole());
        return generateTokenUsingClaims(claims);
    }

    public Boolean validateToken(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return !expiration.before(new Date());
    }

    private String generateTokenUsingClaims(Map<String, Object> claims) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + JWT_TOKEN_VALIDITY * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
