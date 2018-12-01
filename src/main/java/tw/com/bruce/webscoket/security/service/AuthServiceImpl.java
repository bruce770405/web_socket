
package tw.com.bruce.webscoket.security.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tw.com.bruce.webscoket.security.service.model.User;

import java.io.IOException;

/**
 * @program: webscoket
 * @description: 驗證用 service implements
 * @author: BruceHsu
 * @create: 2018-12-01 12:43
 */
@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseEntity<?> register(User userToAdd) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<?> login(String username, String password) {
        return null;
    }

    @Override
    public ResponseEntity<?> refresh(String oldToken) {
        return null;
    }
}
