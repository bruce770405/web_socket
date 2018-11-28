/**
 * @program: webscoket
 * @description: client model
 * @author: BruceHsu
 * @create: 2018-11-02 01:55
 */
package tw.com.bruce.webscoket.controller.chat.param;

import lombok.Data;

@Data
public class ChatClientModel {

    /**
     * 客戶名稱.
     */
    private String clientName;

    /**
     * 夾帶消息.
     */
    private String message;




}
