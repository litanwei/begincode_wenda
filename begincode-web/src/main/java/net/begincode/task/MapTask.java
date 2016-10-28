package net.begincode.task;

import net.begincode.core.handler.CountMapHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Stay on 2016/10/26  19:28.
 */
@Component
public class MapTask {
    @Resource
    private CountMapHandler countMapHandler;

//        @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(fixedRate = 1000*20)  //每隔20秒触发此方法
    public void mapTask(){
        countMapHandler.updateMapToData();
    }
}
