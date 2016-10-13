package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/10/12  13:47.
 */
public enum  FindProResponseEnum implements ResponseEnum{
    PROBLEM_FIND_ERROR("1","查找问题列表失败");

    private String code;
    private String message;

    FindProResponseEnum(String code,String message){
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
