
package tw.com.bruce.webscoket.security.error.handle;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: webscoket
 * @description: 處理驗證錯誤的資訊
 * @author: BruceHsu
 * @create: 2018-11-09 23:48
 */
@Getter
@Setter
public class ErrorHandleBean implements Serializable {

    /**
     * 狀態.
     */
    private String resultStatus;

    /**
     * 訊息.
     */
    private String message;

    /**
     * 錯誤訊息.
     */
    private Exception error;

    /**
     * @Description:
     * @Param: []
     * @return:
     */
    public ErrorHandleBean() {
        super();
        this.resultStatus = "登入失敗";
        this.message = "驗證錯誤";
    }

    /**
     * @Description:
     * @Param: []
     * @return:
     */
    public ErrorHandleBean(String resultStatus, String message) {
        super();
        this.resultStatus = resultStatus;
        this.message = message;
    }

    /**
     * @Description:
     * @Param: []
     * @return:
     */
    public ErrorHandleBean(String resultStatus, String message, Exception error) {
        super();
        this.resultStatus = resultStatus;
        this.message = message;
        this.error = error;
    }

}
