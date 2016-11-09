package net.begincode.task;

import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Problem;
import net.begincode.bean.ConfigBean;
import net.begincode.index.Index;
import net.begincode.manager.IndexConfig;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Stay on 2016/10/2  18:59.
 */
@Component
public class LuceneTask {

    private static Logger logger = LoggerFactory.getLogger(LuceneTask.class);

    @Resource
    private ProblemHandler problemHandler;

    /**
     * 每天凌晨一点重新索引数据库中的内容
     * @throws Exception
     */
//    @Scheduled(cron = "0 0 1 * * *")
    @Scheduled(fixedRate = 1000*20)  //每隔20秒触发此方法
    public void taskIndex(){
        List<Problem> list = problemHandler.selectAllProblem();
        HashSet<ConfigBean> set = new HashSet<ConfigBean>();
        ConfigBean bean = new ConfigBean();
        set.add(bean);
        IndexConfig.setConfig(set);
        Index index = new Index(bean.getIndexName());
        index.deleteAll();
        for (Problem problem : list) {
            Document document = new Document();
            document.add(new StringField("id", problem.getProblemId().toString(), Field.Store.YES));
            document.add(new StringField("solve", problem.getSolve().toString(), Field.Store.YES));
            document.add(new Field("title", problem.getTitle(), TextField.TYPE_STORED));
            document.add(new Field("content", problem.getContent(), TextField.TYPE_STORED));
            index.addDocument(document);
        }
        index.commit();
        logger.info("索引库重新索引"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
