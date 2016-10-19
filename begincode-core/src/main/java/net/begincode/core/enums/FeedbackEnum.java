package net.begincode.core.enums;

import net.begincode.enums.ResponseEnum;

/**
 * Created by saber on 2016/10/12.
 */
public enum FeedbackEnum implements ResponseEnum{
    FEED_BACK("1","违规状态"),
    NO_FEED_BACK("0","正常状态"),
    IS_FEED_BACK("2","审核中"),
    YES_FEED_BACK("3","审核通过");
    private String code;
    private String message;

    FeedbackEnum(String code, String message) {
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
