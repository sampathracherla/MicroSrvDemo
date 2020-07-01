package com.msdemo.handler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenProvider implements Serializable {

	/**
	 * 
	 */
//	private static final long serialVersionUID = 934163944225149922L;

	@Value("classpath:/keys/private.key")
	private String privateKey;
		
	@Value("${msdemo-access-token-validity-seconds}")
	private int validitySeconds;

	@Value("${msdemo-signing-key}")
	private String signingKey;
	
	public Boolean isTokenExpired(String token) {
		Date expiredDate = getExpirationDateFromToken(token);
		return expiredDate.before(new Date());
	}

	public Claims getallClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
	}

 	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claim = getallClaimsFromToken(token);
		return claimsResolver.apply(claim);
	}

}
