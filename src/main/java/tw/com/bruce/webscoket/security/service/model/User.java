/**
 * @program: webscoket
 * @description: 驗證用使用者資料
 * @author: BruceHsu
 * @create: 2018-12-01 12:30
 */
package tw.com.bruce.webscoket.security.service.model;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    /**
     * 伺服器回傳狀態
     */
    private boolean resultStatus;
    /**
     * 伺服器訊息
     */
    private String message;
    /**
     * 序列化物件
     */
    private Object model;

    public boolean isResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

}
