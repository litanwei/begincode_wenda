package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/12/1  21:56.
 */
public enum FindUserResponseEnum implements ResponseEnum {
    USER_FIND_ERROR("1","用户查找失败");
    private String code;
    private String message;

    FindUserResponseEnum(String code, String message) {
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
