package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/10/12.
 */
public enum SolveEnum implements ResponseEnum{
    SOLVE("1","问题解决"),
    NO_SOLVE("0","问题未解决");
    private String code;
    private String message;

    SolveEnum(String code, String message) {
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
