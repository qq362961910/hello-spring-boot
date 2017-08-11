package com.jy.controller.result;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {

    /**
     * 响应码
     * */
    private String code;

    /**
     * 响应消息
     * */
    private String msg;

    /**
     * data
     * */
    private HashMap<String, Object> data = new HashMap<>();

    public String getCode() {
        return code;
    }

    public JsonResult setCode(String code) {
        this.code = code;
        return this;
    }

    public JsonResult setCode(StatusCode code) {
        this.code = code.getValue();
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public JsonResult setData(HashMap<String, Object> data) {
        this.data = data;
        return this;
    }

    public JsonResult addValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
    public JsonResult addValue(Map<String, Object> data) {
        if (data != null && data.size() > 0) {
            this.data.putAll(data);
        }
        return this;
    }

    public static JsonResult newInstance(String code) {
        return new JsonResult().setCode(code);
    }

    public static JsonResult newInstance(StatusCode code) {
        return new JsonResult().setCode(code);
    }

    public static JsonResult newInstance(StatusCode code, HashMap<String, Object> data) {
        return newInstance(code).setData(data);
    }
    public static JsonResult success() {
        return new JsonResult().setCode(StatusCode.SUCCESS);
    }

    public static JsonResult success(String key, Object value) {
        JsonResult result = success();
        result.getData().put(key, value);
        return result;
    }
    public static JsonResult success(HashMap<String, Object> data) {
        return success().setData(data);
    }
    public static JsonResult fail() {
        return new JsonResult().setCode(StatusCode.SERVER_INTERNATL_EXCEPTION);
    }
    public static JsonResult fail(HashMap<String, Object> data) {
        return fail().setData(data);
    }

    public enum StatusCode {

        /**
         * 成功
         * */
        SUCCESS("200"),

        /**
         * 服务器内部异常
         * */
        SERVER_INTERNATL_EXCEPTION("500");

        private String value;

        public String getValue() {
            return value;
        }

        public StatusCode setValue(String value) {
            this.value = value;
            return this;
        }

        StatusCode(String value) {
            this.value = value;
        }
    }
}
