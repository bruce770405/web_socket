
package tw.com.bruce.webscoket.controller.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import tw.com.bruce.webscoket.controller.chat.param.ChatClientModel;
import tw.com.bruce.webscoket.controller.chat.param.ServerResponseModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: webscoket
 * @description: socket controller
 * @author: BruceHsu
 * @create: 2018-11-02 01:45
 */
@Controller
@Slf4j
public class RoomController {

    @Autowired
    //通過SimpMessagingTemplate 發送消息。如果是廣播模式，可以直接使用註解@SendTo
    private SimpMessagingTemplate template;

    /**
     * @Description: 使用者連線資訊.
     * @Param: []
     * @return: void
     */
    @MessageMapping("/")
    public void welcome() {
        log.info("新使用者連線");
        template.convertAndSend("/chat", "歡迎");

    }

    /**
     * @Description: 處理使用者訊息傳送.
     * @Param: [userInfo]
     * @return: void
     */
    @MessageMapping("/send/message")
    public void handleChat(@RequestBody ChatClientModel userInfo) {
        log.info("使用者發送消息 {}, == {} ==",userInfo.getClientName(),userInfo.getMessage());
        //通過SimpMessagingTemplate的convertAndSendToUser向user發送消息。
        //第一參數表示接收信息的user，第二個是web訂閱的address，第三個是消息
        ServerResponseModel returnModel = new ServerResponseModel();
        returnModel.setClientName(userInfo.getClientName());
        returnModel.setMessage(userInfo.getMessage());
        returnModel.setTimeStamp(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

        //將model資料傳入chat URI上，訂閱此連結的用戶可收到訊息
        template.convertAndSend("/chat", returnModel);

    }

}
