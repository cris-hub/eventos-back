//package com.colsubsidio.appeventos.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import java.io.IOException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//import static java.util.Collections.emptyList;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//public class JwtUtil {
//
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    static void addAuthentication(HttpServletResponse res, String username) throws IOException {
//        long time = 600000; //Milisegundos
//        String token = generateToken(username, time);
//        res.addHeader("Authorization", "Bearer " + token);
//
//        time = 86400000; //Milisegundos
//        String refreshToken = generateToken(username, time);
//        res.addHeader("RefreshToken", "Bearer " + refreshToken);
//    }
//
//    static Authentication getAuthentication(HttpServletRequest request, HttpServletResponse res) throws IOException {
//
//        String token = request.getHeader("Authorization");
//        String refreshToken = request.getHeader("RefreshToken");
//        Boolean authorization = true;
//
//        if (token != null) {
//            try {
//                String resulToken = validateToken(token);
//                return resulToken != null
//                        ? new UsernamePasswordAuthenticationToken(resulToken, null, emptyList())
//                        : null;
//
//            } catch (ExpiredJwtException e) {
//                authorization = false;
//            }
//
//            if (!authorization) {
//                try {
//                    String user = validateToken(refreshToken);
//                    addAuthentication(res, user);
//                    return user != null
//                            ? new UsernamePasswordAuthenticationToken(user, null, emptyList())
//                            : null;
//                } catch (ExpiredJwtException e) {
//                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                }
//            }
//        }
//        return null;
//    }
//
//    static String generateToken(String username, Long time) {
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + time))
//                .signWith(SignatureAlgorithm.HS512, "C0lsubs1d10")
//                .compact();
//        return token;
//    }
//
//    static String validateToken(String token) {
//        String resultToken = token.replace("Bearer", "");
//        return resultToken;
//    }
//}
