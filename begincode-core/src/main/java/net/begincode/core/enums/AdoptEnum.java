package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/10/12.
 */
public enum AdoptEnum implements ResponseEnum{
    ADOPT("1","采纳状态"),
    NO_ADOPT("0","非采纳状态");
    private String code;
    private String message;

    AdoptEnum(String code, String message) {
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
