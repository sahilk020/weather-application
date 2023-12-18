package com.jay.authservice.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTGenerator {
	 @Value("${jwt.secret.key}")
	    private String secretkey;

	    public static final long VALIDITY_PERIOD= 60*60*1000;


	    public String generateToken(String username){
	        return Jwts.builder()
	                .setExpiration(new Date(System.currentTimeMillis()+VALIDITY_PERIOD))
	                .setIssuedAt(new Date())
	                .setSubject(username)
	                .setIssuer("jay")
	                .signWith(SignatureAlgorithm.HS256,secretkey)
	                .compact();
	    }

}
