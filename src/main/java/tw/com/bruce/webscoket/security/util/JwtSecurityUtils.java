
package tw.com.bruce.webscoket.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tw.com.bruce.webscoket.controller.login.param.User;
import tw.com.bruce.webscoket.system.WebSocketSystem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static tw.com.bruce.webscoket.system.WebSocketSystem.secret;

/**
 * @program: webscoket
 * @description: JWT認證工具
 * @author: BruceHsu
 * @create: 2018-11-08 23:48
 */
@Component("securityUtils")
public class JwtSecurityUtils {

    /**
     * @Description:
     * @Param: [token]
     * @return: java.lang.String
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @Description:
     * @Param: [token]
     * @return: java.util.Date
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(WebSocketSystem.CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * @Description:
     * @Param: [token]
     * @return: java.util.Date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * @Description:
     * @Param: [token]
     * @return: io.jsonwebtoken.Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * @Description:
     * @Param: []
     * @return: java.util.Date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + WebSocketSystem.expiration * 1000);
    }

    /**
     * @Description:
     * @Param: [token]
     * @return: java.lang.Boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * @Description:
     * @Param: [created, lastPasswordReset]
     * @return: java.lang.Boolean
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * @Description:
     * @Param: [userDetails]
     * @return: java.lang.String
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(WebSocketSystem.CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(WebSocketSystem.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @Description:
     * @Param: [claims]
     * @return: java.lang.String
     */
    String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, WebSocketSystem.secret).compact();
    }

    /**
     * @Description:
     * @Param: [token, lastPasswordReset]
     * @return: java.lang.Boolean
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && !isTokenExpired(token);
    }

    /**
     * @Description:
     * @Param: [token]
     * @return: java.lang.String
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(WebSocketSystem.CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * @Description:
     * @Param: [token, userDetails]
     * @return: java.lang.Boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
//		 final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token)
                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

}
