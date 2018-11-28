/**
 * @program: webscoket
 * @description: JSON轉換工具
 * @author: BruceHsu
 * @create: 2018-11-10 00:04
 */
package tw.com.bruce.webscoket.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tw.com.bruce.webscoket.system.WebSocketSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(JsonUtils.class);

    /**
     * 負責提供轉換Json物件的Mapper.
     */
    private static ObjectMapper objectMapper = WebSocketSystem.getObjectMapper();

    /**
     * 建構子.
     */
    private JsonUtils() {
    }

    /**
     * 物件轉換為Json字串.
     *
     * @param object
     *            轉換的物件.
     * @return String
     */
    public static String getJson(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Json字串轉換為物件.
     * <p>
     * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
     * </p>
     *
     * @param <T>
     *            T.
     * @param json
     *            json字串.
     * @param clazz
     *            欲轉換的Java Class.
     * @return T 轉換後的物件
     * @throws Exception
     *             e
     */
    public static <T> T getObject(String json, Class<T> clazz) throws Exception {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return objectMapper.readValue((String) json, clazz);
        } catch (Exception e) {
            logger.error("json to object error", e);
            throw e;
        }
    }

    /**
     * Json字串轉換為List物件
     * <p>
     * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
     * </p>
     *
     * @param <POJO>
     * @param json
     * @param clazz
     * @return
     */
    public static <POJO> POJO getObject(String json, final TypeReference<POJO> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            logger.error("json to object error", e);
            return null;
        }
    }

    /**
     * Json字串轉換為物件(不產生異常).
     * <p>
     * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
     * </p>
     *
     * @param <T>
     *            T.
     * @param json
     *            json字串.
     * @param clazz
     *            欲轉換的Java Class.
     * @return T 轉換後的物件
     * @throws Exception
     *             e
     */
    public static <T> T getObjectQuietly(String json, Class<T> clazz) throws Exception {
        T object = null;
        try {
            object = getObject(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

        return object;
    }

    /**
     * 將Json轉換為List物件.
     *
     * @param <T>
     *            T.
     * @param json
     *            json字串.
     * @param clazz
     *            欲轉換的Java Class.
     * @return T 轉換後的物件
     * @throws Exception
     *             e
     */
    public static <T> List<T> getList(String json, Class<T> clazz) throws Exception {
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>(0);
        }
        try {

            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));

        } catch (Exception e) {
            logger.error(e.getMessage(), json);
            throw e;
        }
    }

    /**
     * 將Json轉換為List物件.
     *
     * @param <T>
     *            T.
     * @param json
     *            json字串.
     * @param clazz
     *            欲轉換的Java Class.
     * @return T 轉換後的物件
     * @throws Exception
     *             e
     */
    public static <T> List<T> getListQuietly(String json, Class<T> clazz) throws Exception {

        try {

            return getList(json, clazz);

        } catch (Exception e) {
            logger.error(e.getMessage(), json);
            throw e;
        }
    }

    /**
     * Json字串轉換為Map物件.
     *
     * @param <T1>
     *            Map key.
     * @param <T2>
     *            Map value.
     * @param json
     *            json.
     * @param keyClass
     *            keyClass.
     * @param valueClass
     *            valueClass.
     * @return Map
     * @throws Exception
     */
    public static <T1, T2> Map<T1, T2> getMap(String json, Class<T1> keyClass, Class<T2> valueClass) throws Exception {
        try {

            JavaType type = objectMapper.getTypeFactory().constructMapType(HashMap.class, keyClass, valueClass);

            return objectMapper.readValue((String) json, type);

        } catch (Exception e) {
            logger.warn("json get map error {} ", e);
            throw e;
        }
    }

    /**
     * @return {@link #objectMapper}
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
