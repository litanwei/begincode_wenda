package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/9/13.
 */
public enum UserResponseEnum implements ResponseEnum{
    USER_ADD_ERROR("1","用户插入失败");
    private String code;
    private String message;

    UserResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
