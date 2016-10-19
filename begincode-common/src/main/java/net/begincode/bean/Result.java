package net.begincode.bean;

/**
 * Created by yangsj on 2016/1/28.
 */
public class Result {
    private String code;
    private String msg;

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

    public boolean isSuccess() {
        return "0".equals(code) || "0000".equals(code);
    }
}
