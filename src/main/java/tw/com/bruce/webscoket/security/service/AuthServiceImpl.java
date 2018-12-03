
package tw.com.bruce.webscoket.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tw.com.bruce.webscoket.security.controller.param.JwtAuthenticationResponse;
import tw.com.bruce.webscoket.security.service.model.User;
import tw.com.bruce.webscoket.security.util.JwtSecurityUtils;

import java.io.IOException;

/**
 * @program: webscoket
 * @description: 驗證用 service implements
 * @author: BruceHsu
 * @create: 2018-12-01 12:43
 */
@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    // AuthenticationManager 是Spring
    // Secrurity提供的，它根據我们實現的UserDetailServiceImpl去進行用戶驗證
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtSecurityUtils securityUtils;

    // 密碼加密工具
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @Override
    public ResponseEntity<?> register(User userToAdd) throws IOException {
        return null;
    }

    /**
     * @Description: 登入驗證,回傳token
     * @Param: [username, password]
     * @return: org.springframework.http.ResponseEntity<?>
     */
    @Override
    public ResponseEntity<?> login(String username, String password) {
        JwtAuthenticationResponse rs = new JwtAuthenticationResponse();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = securityUtils.generateToken(userDetails);
        rs.setToken(token);

        return ResponseEntity.ok(rs);
    }

    @Override
    public ResponseEntity<?> refresh(String oldToken) {
        return null;
    }
}
