package net.begincode.core.enums;

/**
 * Created by saber on 2016/10/11.
 */
public enum ProblemEnum {


    DEL_FLAG(1), //删除状态
    NO_DEL_FLAG(0),//可用状态
    SOLVE(1),//解决状态
    NO_SOLVE(0),//未解决状态
    FEED_BACK(1),//违规状态
    NO_FEED_BACK(0),//正常状态
    IS_FEED_BACK(2),//审核中
    YES_FEED_BACK(3);//审核通过

    private String stringValue;
    private int intVlue;

    ProblemEnum(String stringValue){
        this.stringValue = stringValue;
    }

    ProblemEnum(int intVlue){
        this.intVlue = intVlue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntVlue() {
        return intVlue;
    }


}
