/**
 * @program: webscoket
 * @description: websocket 驗證用攔截器
 * @author: BruceHsu
 * @create: 2018-12-01 02:01
 */
package tw.com.bruce.webscoket.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tw.com.bruce.webscoket.security.util.JwtSecurityUtils;
import tw.com.bruce.webscoket.system.WebSocketSystem;

@Component
@Slf4j
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    /**使用者登入驗證.*/
    private UserDetailsService userDetailsService;

    /**登入驗證工具.*/
    private JwtSecurityUtils securityUtils;


    @Autowired
    public AuthChannelInterceptorAdapter(UserDetailsService userDetailsService,JwtSecurityUtils securityUtils) {
        this.userDetailsService = userDetailsService;
        this.securityUtils = securityUtils;
    }

    /**
     * @Description: 首次登入攔截驗證JWT token
     * @Param: [message, channel]
     * @return: org.springframework.messaging.Message<?>
     */
    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {

        //
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            String jwtToken = accessor.getFirstNativeHeader(WebSocketSystem.tokenHeader); // The part after "auth "

            String username = securityUtils.getUsernameFromToken(jwtToken);

            log.info("checking authentication " + username);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (securityUtils.validateToken(jwtToken, userDetails)) {
                //檢核通過
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(rq));
                log.info("authenticated user " + username + ", setting security context");
                accessor.setUser(authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        return message;
    }

}
