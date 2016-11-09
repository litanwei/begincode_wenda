package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/10/28  16:41.
 */
public enum CollectEnum implements ResponseEnum {
    COLLECT("1", "收藏状态"),
    NO_COLLECT("0", "非收藏状态");
    private String code;
    private String message;

    CollectEnum(String code, String message) {
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
