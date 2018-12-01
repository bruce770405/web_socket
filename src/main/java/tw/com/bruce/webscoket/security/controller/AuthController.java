/**
 * @program: webscoket
 * @description: 驗證用controller
 * @author: BruceHsu
 * @create: 2018-12-01 12:26
 */
package tw.com.bruce.webscoket.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.com.bruce.webscoket.security.controller.param.JwtAuthenticationRequest;
import tw.com.bruce.webscoket.security.service.AuthService;
import tw.com.bruce.webscoket.security.service.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value = "/auth")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    /**
     * 登入驗證
     * @param authRequest
     * @return
     * @throws AuthenticationException
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) throws AuthenticationException {
        return authService.login(authRequest.getUsername(),authRequest.getPassword());
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        return authService.refresh(token);
    }


    /**
     * 註冊
     * @param addedUser
     * @return
     * @throws AuthenticationException
     * @throws IOException
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User addedUser) throws AuthenticationException, IOException {
        return authService.register(addedUser);
    }

}
