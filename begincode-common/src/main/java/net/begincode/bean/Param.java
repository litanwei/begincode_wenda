package net.begincode.bean;

import net.begincode.common.BizException;
import net.begincode.enums.ResponseEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yangsj on 2016/1/26.
 */
public abstract class Param {

    public abstract void check();

    public void checkNotNull(Object value, ResponseEnum status) {
        checkArgs(value != null, status);
    }

    public void checkNotEmpty(String value, ResponseEnum status) {
        checkArgs(StringUtils.isNotBlank(value), status);
    }


    public void checkArgs(boolean success, ResponseEnum status) {
        if (!success) {
            throw new BizException(status);
        }
    }
}
