/**
 * @program: webscoket
 * @description: 登入者資料認證service
 * @author: BruceHsu
 * @create: 2018-11-08 22:54
 */
package tw.com.bruce.webscoket.security.filter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    
    /**
     * @Description:
     * @Param: [s] the s
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
