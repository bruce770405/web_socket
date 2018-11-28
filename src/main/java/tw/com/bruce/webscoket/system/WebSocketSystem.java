
package tw.com.bruce.webscoket.system;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

/**
 * @program: webscoket
 * @description: 系統參數
 * @author: BruceHsu
 * @create: 2018-11-09 00:06
 */
public class WebSocketSystem {

    /** */
    @Value("${jwt.header}")
    public static String CLAIM_KEY_USERNAME = "sub";

    /** */
    @Value("${jwt.header}")
    public static String CLAIM_KEY_CREATED = "created";

    /** */
    @Value("${jwt.header}")
    public static String secret = "bruce"; //token密鑰

    @Value("${jwt.header}")
    public static String tokenHeader;

    @Value("${jwt.tokenHead}")
    public static String tokenHead;


    /**
     * JWT 到期時間.
     */
//    @Value("${jwt.expiration}")
    public static final Long expiration = (long) 604800;

    /**
     * 負責提供轉換Json物件的Mapper.
     */
    private static ObjectMapper objectMapper;

    static {
        registerObjectMapper();
    }

    /**
     * 設定JSON與JAVA物件的轉換規則.
     */
    private static void registerObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setVisibility(objectMapper.getSerializationConfig().getDefaultVisibilityChecker() //
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.NONE) //
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        // objectMapper.setDateFormat(dateFormat) //時間格式轉換規則
    }

    /**
     * @return {@link #objectMapper}
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
