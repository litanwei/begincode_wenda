package net.begincode.common;

import net.begincode.enums.ResponseEnum;

/**
 * Created by yangsj on 2016/8/20.
 * 业务异常
 */
public class BizException extends  RuntimeException{
    private ResponseEnum status;

    public BizException(ResponseEnum status) {
        super();
        this.status = status;
    }

    public ResponseEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseEnum status) {
        this.status = status;
    }

}
