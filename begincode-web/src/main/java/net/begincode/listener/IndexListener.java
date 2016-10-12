package net.begincode.listener;

import net.begincode.lucene.bean.ConfigBean;
import net.begincode.lucene.manager.IndexConfig;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * tomcat启动时索引收集启动
 * Created by Stay on 2016/9/28  22:41.
 */
@Component
public class IndexListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null)
        {
            HashSet<ConfigBean> set = new HashSet<ConfigBean>();
            ConfigBean bean = new ConfigBean();
            set.add(bean);
            IndexConfig.setConfig(set);
        }
    }
}
