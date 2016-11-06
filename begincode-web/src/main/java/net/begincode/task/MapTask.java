package net.begincode.task;

import net.begincode.core.handler.ProblemHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by Stay on 2016/10/26  19:28.
 */
@Component
public class MapTask {


    @Resource
    private ProblemHandler problemHandler;



//        @Scheduled(fixedRate = 1000*20)  //每隔20秒触发此方法
    @Scheduled(fixedRate = 1000 * 300)  //每隔五分钟触发此方法
    public void mapTask() {
        problemHandler.forceUpdateViewToData();
        problemHandler.updateMapToData();
    }

    //服务器关闭前执行更新
    @PreDestroy
    public void destroy() {
        problemHandler.forceUpdateViewToData();
        problemHandler.updateMapToData();
    }
}
