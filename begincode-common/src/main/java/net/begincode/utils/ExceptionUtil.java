package net.begincode.utils;

import net.begincode.common.BizException;
import net.begincode.enums.ResponseEnum;
import org.slf4j.Logger;

/**
 * Created by Stay on 2016/12/12  18:26.
 */
public class ExceptionUtil {
    private ExceptionUtil(){}
    public static void ThrowUpdateBizException(Integer updateInt, Logger logger, ResponseEnum responseEnum){
        if(updateInt<0){
            logger.error(responseEnum.getMessage());
            throw new BizException(responseEnum);
        }
    }
}
