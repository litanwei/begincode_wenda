package net.begincode.enums;

/**
 * Created by yangsj on 2016/8/21.
 */
public enum DelFlagEnum {
    IS_DEL(1,"已删除"),NO_DEL(0,"未删除");
    private Integer code;
    private String message;

    DelFlagEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
