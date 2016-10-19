package net.begincode.core.handler;

import net.begincode.core.service.ProAttentionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/10/17  17:13.
 */
@Component
public class ProAttentionHandler {
    @Resource
    private ProAttentionService proAttentionService;

    /**
     * 根据userId返回用户收藏的问题总数
     * @param userId
     * @return
     */
    public Integer selectCollectNumByUserId(Integer userId){
        return proAttentionService.selectCollectNumByUserId(userId);
    }


}
