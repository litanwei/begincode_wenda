package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by yangsj on 2016/8/20.
 * 业务响应枚举
 */
public enum BizResponseEnum implements ResponseEnum {
    SYSTEM_ERROR("1","系统错误");
    private String code;
    private String message;

    BizResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
