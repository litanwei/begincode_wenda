package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/12/12  18:41.
 */
public enum AnsSetResponseEnum implements ResponseEnum {
    ANSWER_OPERATION_ERROR("2","赞同反对操作失败");
    private String code;
    private String message;

    AnsSetResponseEnum(String code, String message) {
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
