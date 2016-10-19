package net.begincode.bean;


import net.begincode.enums.CommonResponseEnum;
import net.begincode.enums.ResponseEnum;

import java.text.MessageFormat;

/**
 * Created by yangsj on 2016/1/26.
 */
public class Response {
    private String code;
    private String msg;
    private Object data;
    private long _time;

    public Response(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this._time = System.currentTimeMillis();
    }

    public static Response success() {
        return new Response(CommonResponseEnum.SUCCESS.getCode(), CommonResponseEnum.SUCCESS.getMessage(), null);
    }

    public static Response success(Object data) {
        return new Response(CommonResponseEnum.SUCCESS.getCode(), CommonResponseEnum.SUCCESS.getMessage(), data);
    }

    public static Response failed(ResponseEnum status) {
        return new Response(status.getCode(), status.getMessage(), null);
    }

    public static Response failed(ResponseEnum status, Object[] messages) {
        return new Response(status.getCode(), MessageFormat.format(status.getMessage(), messages), null);
    }

    public static Response failed(ResponseEnum status, Object data) {
        return new Response(status.getCode(), status.getMessage(), data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long get_time() {
        return _time;
    }

    public void set_time(long _time) {
        this._time = _time;
    }
}
