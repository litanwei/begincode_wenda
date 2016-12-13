package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/12/12  18:13.
 */
public enum AddAnsAgreeResponseEnum implements ResponseEnum {
    ANSAGREE_ADD_ERROR("1","AnsAgree插入失败");
    private String code;
    private String message;

    AddAnsAgreeResponseEnum(String code, String message) {
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
