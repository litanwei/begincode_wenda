package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/9/13.
 */
public enum OpenIdResponseEnum implements ResponseEnum{
    OPENID_FIND_ERROR("1","openId不能为空");
    private String code;
    private String message;

    OpenIdResponseEnum(String code, String message) {
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
