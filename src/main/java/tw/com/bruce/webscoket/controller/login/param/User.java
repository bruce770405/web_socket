/**
 * @program: webscoket
 * @description: 登入使用者資訊
 * @author: BruceHsu
 * @create: 2018-11-09 00:18
 */
package tw.com.bruce.webscoket.controller.login.param;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class User implements UserDetails {

    /**
     * 使用者帳戶.
     */
    private String userName;


    /**
     * 使用者密碼.
     */
    private String password;

    /**
     * @Description: 使用者權限
     */
    private final Collection<? extends GrantedAuthority> authorities;
    
    /**
     * @Description: 最後密碼更新時間
     */
    private final Date lastPasswordResetDate;

    /**
     * @Description: 回傳使用者名稱
     * @Param: []
     * @return: java.lang.String
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * @Description: 回傳使用者帳戶是否註銷
     * @Param: []
     * @return: java.lang.String
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * @Description: 使用者帳戶是否上鎖
     * @Param: []
     * @return: java.lang.String
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * @Description: 密碼是否過期
     * @Param: []
     * @return: java.lang.String
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * @Description: 回傳使用者帳戶是否啟用
     * @Param: []
     * @return: java.lang.String
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    // 返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
