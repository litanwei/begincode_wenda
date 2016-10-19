package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/10/12.
 */
public enum DeleteFlagEnum implements ResponseEnum{
    DEL_FLAG("1","删除状态"),
    NO_DEL_FLAG("0","可用状态");
    private String code;
    private String message;

    DeleteFlagEnum(String code, String message) {
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
