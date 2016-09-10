package net.begincode.core.mapper;

import net.begincode.core.model.BegincodeUser;
import org.springframework.stereotype.Repository;

/**
 * Created by saber on 2016/9/10.
 */
@Repository
public interface BizBegincodeUserMapper extends BegincodeUserMapper {

    BegincodeUser selectByTokenIdAndOpenId(BegincodeUser record);
}
