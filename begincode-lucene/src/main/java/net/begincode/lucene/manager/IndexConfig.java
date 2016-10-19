package net.begincode.lucene.manager;

import net.begincode.lucene.bean.ConfigBean;

import java.util.HashSet;

/**
 * Created by Stay on 2016/9/28  17:17.
 */
public class IndexConfig {
    // 系统中配置多个索引
    private static HashSet<ConfigBean> configBeans;

    private static class DefaultIndexConfig {
        private static final HashSet<ConfigBean> configBeansDefault = new HashSet<ConfigBean>();
        static {
            ConfigBean bean = new ConfigBean();
            configBeansDefault.add(bean);
        }
    }

    public static HashSet<ConfigBean> getConfig() {
        if (configBeans == null) {
            // 如果configBeans为空，返回系统默认值
            return DefaultIndexConfig.configBeansDefault;
        }
        return configBeans;
    }

    /**
     * 设置系统索引配置
     * @param configBeans
     */
    public static void setConfig(HashSet<ConfigBean> configBeans) {
        IndexConfig.configBeans = configBeans;
    }
}
