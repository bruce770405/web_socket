///**
// * @program: webscoket
// * @description:
// * @author: BruceHsu
// * @create: 2018-12-01 01:58
// */
//package tw.com.bruce.webscoket.security.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.Collections;
//
//@Component
//public class WebSocketAuthenticatorService {
//
//    @Autowired
//    UserDetailServiceImpl userDetailService;
//
//    // This method MSUT return a UsernamePasswordAuthenticationToken, another component in the security chain is testing it with 'instanceof'
//    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(final String  username, final String password) throws AuthenticationException {
//        if (!StringUtils.hasText(username)) {
//            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
//        }
//        if (!StringUtils.hasText(password)) {
//            throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
//        }
//        // Add your own logic for retrieving user in fetchUserFromDb()
//        if (userDetailService.loadUserByUsername(username, password) == null) {
//            throw new BadCredentialsException("Bad credentials for user " + username);
//        }
//
//        // null credentials, we do not pass the password along
//        return new UsernamePasswordAuthenticationToken(
//                username,
//                null,
//                Collections.singleton((GrantedAuthority) () -> "USER") // MUST provide at least one role
//        );
//    }
//}
