package net.begincode.core.model;

/**
 * Created by yangsj on 2016/11/13.
 */
public class BizBegincodeUser extends BegincodeUser {
    private Integer  activeCount; //活跃指数=提问数+回复数

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }
}
