package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/11/4.
 */
public enum AgreeEnum implements ResponseEnum{
    OPPOSITION("2","反对"),
    AGREE("1","赞同"),
    NO_AGREE("0","默认状态");
    private String code;
    private String message;

    AgreeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
