
package tw.com.bruce.webscoket.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tw.com.bruce.webscoket.security.util.JwtSecurityUtils;
import tw.com.bruce.webscoket.system.WebSocketSystem;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 保證一次請求
 *
 * @program: webscoket
 * @description:
 * @author: BruceHsu
 * @create: 2018-11-08 12:20
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    /**使用者登入驗證.*/
    private UserDetailsService userDetailsService;

    @Autowired
    /**登入驗證工具.*/
    private JwtSecurityUtils securityUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest rq, HttpServletResponse rs, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = rq.getHeader(WebSocketSystem.tokenHeader);

        if (authHeader != null && authHeader.startsWith(WebSocketSystem.tokenHead)) {
            final String authToken = authHeader.substring(WebSocketSystem.tokenHead.length()); // The part after "auth "
            String username = securityUtils.getUsernameFromToken(authToken);

            logger.info("checking authentication " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
                if (securityUtils.validateToken(authToken, userDetails)) {
                    //檢核通過
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(rq));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(rq, rs);
    }

}
