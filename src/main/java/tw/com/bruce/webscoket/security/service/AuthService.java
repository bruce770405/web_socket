/**
 * @program: webscoket
 * @description: 驗證用 service
 * @author: BruceHsu
 * @create: 2018-12-01 12:27
 */
package tw.com.bruce.webscoket.security.service;

import org.springframework.http.ResponseEntity;
import tw.com.bruce.webscoket.security.service.model.User;

import java.io.IOException;

public interface AuthService {

    /**
     * @Description: 
     * @Param: [userToAdd]
     * @return: org.springframework.http.ResponseEntity<?>
     */
    ResponseEntity<?> register(User userToAdd) throws IOException;
    
    /**
     * @Description: 
     * @Param: [username, password]
     * @return: org.springframework.http.ResponseEntity<?>
     */
    ResponseEntity<?> login(String username, String password);
    
    /**
     * @Description: 
     * @Param: [oldToken]
     * @return: org.springframework.http.ResponseEntity<?>
     */
    ResponseEntity<?> refresh(String oldToken);
    
}
