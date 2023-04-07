package com.hotstrip.runapi.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.key.Jsr310NullKeySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * JacksonUtil
 *
 * @author hotstrip
 */
public class JacksonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(JacksonUtil.class);

    static {
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //支持key为null
        MAPPER.getSerializerProvider().setNullKeySerializer(new Jsr310NullKeySerializer());
    }

    private JacksonUtil() {
    }

    public static String toJsonString(Object object) {
        if (object == null) {
            throw new RuntimeException("object is null, unable to convert");
        }

        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON transformation error");
        }
    }

    public static String toJsonStringQuietly(Object object) {
        if (object == null) {
            LOG.warn("object is null");
            return null;
        }

        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.warn("JSON transformation error");
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> cla, String exceptionContent) {
        checkJsonString(json);

        try {
            return MAPPER.readValue(json, cla);
        } catch (IOException e) {
            if ("".equals(exceptionContent) || exceptionContent == null) {
                throw new RuntimeException("json string cannot be converted to object");
            }
            throw new RuntimeException(exceptionContent);
        }
    }

    public static <T> T toObject(String json, Class<T> cla) {
        return toObject(json, cla, null);
    }

    public static <T> T toObjectQuietly(String json, Class<T> cla) {
        checkJsonStringQuietly(json);

        try {
            return MAPPER.readValue(json, cla);
        } catch (IOException e) {
            LOG.warn("json string cannot be converted to object");
        }
        return null;
    }

    public static <T> List<T> toArray(String json, Class<T> cla) {
        checkJsonString(json);

        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, cla);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException("json string cannot be converted to array");
        }
    }

    public static <T> List<T> toArrayQuietly(String json, Class<T> cla) {
        checkJsonStringQuietly(json);

        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, cla);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            LOG.warn("json string cannot be converted to array");
        }
        return null;
    }

    private static void checkJsonStringQuietly(String json) {
        if (json == null) {
            throw new RuntimeException("json is null");
        }
        if ("".equals(json)) {
            throw new RuntimeException("json is empty");
        }
    }

    private static void checkJsonString(String json) {
        if (json == null) {
            LOG.warn("json is null");
        }
        if ("".equals(json)) {
            LOG.warn("json is empty");
        }
    }

    public static <T> T toObject(String json, Type type) {
        checkJsonString(json);

        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructType(type));
        } catch (Exception e) {
            throw new RuntimeException("json string cannot be converted to type");
        }
    }

    public static <T> T toObjectQuietly(String json, Type type) {
        checkJsonStringQuietly(json);

        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructType(type));
        } catch (Exception e) {
            LOG.warn("json string cannot be converted to type");
        }
        return null;
    }

    public static <T> T toObject(InputStream in, Class<T> cla) {
        if (in == null) {
            throw new RuntimeException("InputStream is null");
        }

        try {
            return MAPPER.readValue(in, cla);
        } catch (IOException e) {
            throw new RuntimeException("json string cannot be converted");
        }
    }

    public static <T> T toObjectQuietly(InputStream in, Class<T> cla) {
        if (in == null) {
            LOG.warn("InputStream is null");
        }

        try {
            return MAPPER.readValue(in, cla);
        } catch (IOException e) {
            LOG.warn("json string cannot be converted");
        }
        return null;
    }

    public static <T> T toObject(Reader reader, Class<T> cla) {
        if (reader == null) {
            throw new RuntimeException("Reader is null");
        }

        try {
            return MAPPER.readValue(reader, cla);
        } catch (IOException e) {
            throw new RuntimeException("json string cannot be converted");
        }
    }

    public static <T> T toObjectQuietly(Reader reader, Class<T> cla) {
        if (reader == null) {
            LOG.warn("Reader is null");
        }

        try {
            return MAPPER.readValue(reader, cla);
        } catch (IOException e) {
            LOG.warn("json string cannot be converted");
        }
        return null;
    }
}
