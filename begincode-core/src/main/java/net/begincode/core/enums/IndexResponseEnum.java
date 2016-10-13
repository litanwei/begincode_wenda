package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/10/13  10:16.
 */
public enum IndexResponseEnum implements ResponseEnum {
    INDEX_ERROR("1","索引库异常");

    private String code;
    private String message;

    IndexResponseEnum(String code,String message){
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
