package com.hiep.todolist.auth;


import java.security.Key;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.hiep.todolist.entity.User;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenHelper {
	private static final long EXPIRATION_LIMIT_IN_MINUTES=30;
	private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	private static final String SECRET_KEY = "CWEXFYH2J3K5N6P7R9SATCVDWEXGZH2J4M5N6Q8R9SBUCVDXFYGZJ3K4M6";
	 
    private static final String ISSUER = "https://todolis.com";
	private JwtTokenHelper() {
        super();
    }
	
	public static String createJWT(User user) {
		long currentTimeInMillis = System.currentTimeMillis();
		Date now = new Date(currentTimeInMillis);
		long expirationTimeInMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT_IN_MINUTES);
		Date expirationDate = new Date(currentTimeInMillis + expirationTimeInMilliSeconds);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("roles", user.getRoles());
        
        System.out.println((String) claims.get("roles"));
        
        JwtBuilder builder = Jwts.builder() // Configured and then used to create JWT compact serialized strings
                .setClaims(claims).setId(UUID.randomUUID().toString()) // Sets the JWT Claims jti (JWT ID) value
                .setIssuedAt(now) // Sets the JWT Claims iat (issued at) value
                .setIssuer(ISSUER) // Sets the JWT Claims iss (issuer) value
                .setExpiration(expirationDate) // Sets the JWT Claims exp (expiration) value
                .signWith(signingKey, SIGNATURE_ALGORITHM);
 
        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
	}
	
	public static User getUserFromToken(String token) {
        final Claims claims = decodeJWT(token);
        User user = new User();
        user.setUserName(claims.getSubject());
        user.setRoles((String) claims.get("roles"));
        return user;
    }
	
    public static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
 
    private static Date getExpirationDateFromToken(String token) {
        return (Date) getClaimFromToken(token, Claims::getExpiration);
    }
 
    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = decodeJWT(token);
        return claimsResolver.apply(claims);
    }
	
    private static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser() // Configured and then used to parse JWT strings
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)) // Sets the signing key used to verify
                                                                                // any discovered JWS digital signature
                .parseClaimsJws(jwt) // Parses the specified compact serialized JWS string based
                .getBody();
    }
	 
}
