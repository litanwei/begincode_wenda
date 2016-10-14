package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/10/13.
 */
public enum ContentResponseEnum implements ResponseEnum{
    CONTENT_EMPTY_ERROR("1","content不能为空");
    private String code;
    private String message;

    ContentResponseEnum(String code, String message) {
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
