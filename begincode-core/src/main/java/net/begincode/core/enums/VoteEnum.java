package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by Stay on 2016/10/28  16:46.
 */
public enum VoteEnum implements ResponseEnum {
    VOTE("1", "投票状态"),
    NO_VOTE("0", "非投票状态");
    private String code;
    private String message;

    VoteEnum(String code, String message) {
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
