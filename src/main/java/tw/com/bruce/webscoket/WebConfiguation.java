
package tw.com.bruce.webscoket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: webscoket
 * @description: 設定資料
 * @author: BruceHsu
 * @create: 2018-11-02 01:38
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebConfiguation implements WebSocketMessageBrokerConfigurer {


    /**
     * 配製訊息代理(Message broker)
     *
     * @param config the MessageBrokerRegistry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app").
                enableSimpleBroker("/chat");

    }

    /**
     * 註冊一個節點,用來映射指定URL,方法內註冊一個STOMP的endpoint,並且指定使用SockJS協定
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }

}
