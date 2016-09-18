package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by yangsj on 2016/9/9.
 */
public enum ProblemResponseEnum implements ResponseEnum {
    PROBLEM_ADD_ERROR("100","问题插入失败");
    private String code;
    private String message;

    ProblemResponseEnum(String code, String message) {
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
