package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/11/14  17:50.
 */
public enum LabelResponseEnum implements ResponseEnum {
    LABEL_ADD_ERROR("100","标签为空或者异常");
    private String code;
    private String message;

    LabelResponseEnum(String code, String message) {
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
