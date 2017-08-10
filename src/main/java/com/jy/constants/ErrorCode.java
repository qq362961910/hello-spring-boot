package com.jy.constants;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class ErrorCode {

    @Describe("年龄为空")
    public static final String AGE_NULL = "1";

    @Describe("罩杯为空")
    public static final String CUP_SIZE_NULL = "2";

    @Describe("主键为空")
    public static final String PRIMARY_KEY_NULL = "3";

    @Describe("密码格式不正确")
    public static final String PASSWORD_PATTERN = "4";

    @Describe("服务器内部异常")
    public static final String SERVER_INTERNAL_EXCEPTION = "500";


    private static final Map<String, String> ERROR_CODE_DESCRIBE_MAPPING = new HashMap<>();

    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface Describe {
        String value();
    }

    static {
        for (Field f: ErrorCode.class.getDeclaredFields()) {
            Describe describe = f.getAnnotation(Describe.class);
            if (describe != null) {
                if (!Modifier.isPublic(f.getModifiers())) {
                    f.setAccessible(true);
                }
                try { ERROR_CODE_DESCRIBE_MAPPING.put((String)f.get(ErrorCode.class), describe.value()); } catch (IllegalAccessException e) { }
            }
        }
    }

    public static String getDescribe(String code) {
        return ERROR_CODE_DESCRIBE_MAPPING.getOrDefault(code, "未找到异常编码描述");
    }

    public static void main(String[] args) {
        System.out.println(getDescribe(SERVER_INTERNAL_EXCEPTION));
    }
}
