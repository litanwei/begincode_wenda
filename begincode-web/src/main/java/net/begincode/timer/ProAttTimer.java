package net.begincode.timer;


import net.begincode.core.service.ProAttentionService;
import net.begincode.variate.ProblemVariate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class ProAttTimer {
    @Resource
    private ProAttentionService proAttentionService;
    private Logger logger = LoggerFactory.getLogger(ProAttTimer.class);

    //服务器关闭前执行更新
    @PreDestroy
    public void destroy() {
        ProblemVariate.updateMap();
        logger.info("结束提交了本地map数据");
    }

    //每小时执行一次更新
    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void run() {
        ProblemVariate.updateMap();
    }

}
