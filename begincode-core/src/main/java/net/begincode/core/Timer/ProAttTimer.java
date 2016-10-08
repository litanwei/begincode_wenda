package net.begincode.core.Timer;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import net.begincode.core.service.ProAttentionService;
import net.begincode.core.variate.ProblemVariate;

@Controller
public class ProAttTimer  {
	 @Resource
	 private ProAttentionService proAttentionService;
	//服务器关闭前执行更新
	@PreDestroy
	public void destroy() {
    	ProblemVariate.updateMap();
    	System.out.println("结束了");
	}
	//每小时执行一次更新
	@Scheduled(initialDelay=3600000,fixedDelay = 3600000)
	public void run() {
		ProblemVariate.updateMap();
	}

}
