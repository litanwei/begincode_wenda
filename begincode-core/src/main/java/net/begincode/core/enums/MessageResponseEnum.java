package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/10/23  17:06.
 */
public enum MessageResponseEnum implements ResponseEnum {
    MESSAGE_UPDATE_ERROR("1","消息表更改状态失败");
    private String code;
    private String message;

    MessageResponseEnum(String code, String message) {
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
