package net.begincode.core.Timer;


import net.begincode.core.forbidden.ForbiddenWordFilter;
import net.begincode.core.variate.ProblemVariate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Controller
public class ProAttTimer  {


	private Logger logger = LoggerFactory.getLogger(ProAttTimer.class);


    @Resource
    private ForbiddenWordFilter forbiddenWordFilter;

	/**
	 * 系统启动时加载违禁字字典（forbiddenWords.txt）
	 * */
	@PostConstruct
	public void init() {
		forbiddenWordFilter.readForbiddenWord(false);
		logger.info("加载敏感字");
	}

	//服务器关闭前执行更新
	@PreDestroy
	public void destroy() {
		ProblemVariate.updateMap();
		logger.info("结束提交了本地map数据");
	}
	//每小时执行一次更新
	@Scheduled(initialDelay=3600000,fixedDelay = 3600000)
	public void run() {
		ProblemVariate.updateMap();
	}

}
