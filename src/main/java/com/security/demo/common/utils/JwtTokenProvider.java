package com.security.demo.common.utils;


import com.security.demo.common.component.AuthParameters;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component

public class JwtTokenProvider {

    Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private AuthParameters authParameters;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * Generate token for user login.
     *
     * @param username
     * @return return a token string.
     */

    public String createJwtToken(String username) {

        //expire time

        Date expireTime = new Date(System.currentTimeMillis() + authParameters.getTokenExpiredMs());

        //create token

        String token = Jwts.builder()

                .setSubject(username)

                .setExpiration(expireTime)

                .setIssuedAt(new Date())

                .signWith(SignatureAlgorithm.HS512, authParameters.getJwtTokenSecret())

                .compact();

        redisTemplate.opsForValue().set(token, username, authParameters.getTokenExpiredMs(), TimeUnit.MILLISECONDS);

        return token;

    }

    /**
     * validate token eligible.
     * <p>
     * if Jwts can parse the token string and no throw any exception, then the token is eligible.
     *
     * @param token a jws string.
     */

    public String validateToken(String token) {

        String VALIDATE_FAILED = "validate failed : ";

        try {
            Jwts.parser()
                    .setSigningKey(authParameters.getJwtTokenSecret())
                    .parseClaimsJws(token);

            return redisTemplate.opsForValue().get(token);

        } catch (Exception ex) {

            //ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, //IllegalArgumentException

            logger.error(VALIDATE_FAILED + ex.getMessage());

            return null;

        }

    }

    /**
     * delete token
     *
     * @param token
     */
    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }

}

