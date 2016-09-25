package net.begincode.core.enums;

/**
 * Created by saber on 2016/9/13.
 */
public enum AnswerEnum {

    DEL_FLAG(1), //删除状态
    NO_DEL_FLAG(0),//可用状态
    FEED_BACK(1),//违规状态
    NO_FEED_BACK(0),//正常状态
    IS_FEED_BACK(2),//审核中
    YES_FEED_BACK(3),//审核通过
    ANSWER_ADOPT(1),//采纳状态
    NO_ANSWER_ADOPT(0);//非采纳状态

    private String stringValue;
    private int intVlue;

    AnswerEnum(String stringValue){
        this.stringValue = stringValue;
    }

    AnswerEnum(int intVlue){
        this.intVlue = intVlue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntVlue() {
        return intVlue;
    }



}
