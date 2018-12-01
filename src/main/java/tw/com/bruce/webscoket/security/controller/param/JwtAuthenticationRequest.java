/**
 * @program: webscoket
 * @description: Jwt Request auth Params
 * @author: BruceHsu
 * @create: 2018-12-01 12:40
 */
package tw.com.bruce.webscoket.security.controller.param;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 2225926999428924375L;

    /**
     * @Description: 使用者名稱.
     * @Param:
     * @return:
     */
    private String username;

    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
