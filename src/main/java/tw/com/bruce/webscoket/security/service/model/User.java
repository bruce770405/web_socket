/**
 * @program: webscoket
 * @description: 驗證用使用者資料
 * @author: BruceHsu
 * @create: 2018-12-01 12:30
 */
package tw.com.bruce.webscoket.security.service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    /** 伺服器回傳狀態.*/
    private boolean resultStatus;

    /**  帳戶. */
    private String userName;

    /** 密碼.*/
    private String password;

}
