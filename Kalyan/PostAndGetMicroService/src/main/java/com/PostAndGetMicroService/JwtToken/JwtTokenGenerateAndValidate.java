package com.PostAndGetMicroService.JwtToken;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenGenerateAndValidate {
	
	
	//Token Generation From Here
	
	private static final String SecretKey = "214125442A472D4B6150645367556B58703273357638792F423F4528482B4D62";
		
;

	public String generateToken(String username) {
		
		Map<String,Object> claims = new HashMap<>();
		
		return createToken(claims,username);
		
	}

	public String createToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(username)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				   .signWith(getSignKey(), SignatureAlgorithm.HS256)
				   .compact();
	}

	private Key getSignKey() {
		
		byte[] keybytes = Decoders.BASE64.decode(SecretKey);
		
		return Keys.hmacShaKeyFor(keybytes);
	}

	// Token Validation From Here
	
    public String extractUsernameFromToken(String Token) {
        return extractClaim(Token, Claims::getSubject);
    }

    public Date extractExpiration(String Token) {
        return extractClaim(Token, Claims::getExpiration);
    }

    public <T> T extractClaim(String Token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(Token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String Token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(Token)
                .getBody();
    }

    private Boolean isTokenExpired(String Token) {
        return extractExpiration(Token).before(new Date());
    }

    public Boolean validateToken(String Token, UserDetails userDetails) {
        final String username = extractUsernameFromToken(Token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(Token));
    }
	
	
	


}
