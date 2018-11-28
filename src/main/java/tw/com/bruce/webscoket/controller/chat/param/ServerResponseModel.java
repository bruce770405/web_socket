/**
 * @program: webscoket
 * @description: server model
 * @author: BruceHsu
 * @create: 2018-11-02 01:56
 */
package tw.com.bruce.webscoket.controller.chat.param;

import lombok.Data;

@Data
public class ServerResponseModel {

    /**
     * 客戶名稱.
     */
    private String clientName;

    /**
     * 夾帶消息.
     */
    private String message;

    /**
     * 伺服器時間搓際.
     */
    private String timeStamp;

}
