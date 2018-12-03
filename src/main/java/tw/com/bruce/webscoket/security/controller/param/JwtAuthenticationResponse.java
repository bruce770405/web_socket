
package tw.com.bruce.webscoket.security.controller.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: webscoket
 * @description: jwt 驗證回傳結果
 * @author: BruceHsu
 * @create: 2018-12-03 19:35
 */
@Data
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    /** jwt token. */
    private  String token;

}
