package net.begincode.task;

import net.begincode.core.handler.ProblemHandler;
import net.begincode.core.model.Problem;
import net.begincode.bean.ConfigBean;
import net.begincode.index.Index;
import net.begincode.manager.IndexConfig;
import net.begincode.utils.PatternUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Stay on 2016/10/2  18:59.
 */
@Component
public class LuceneTask {

    @Resource
    private ProblemHandler problemHandler;

    /**
     * 每天凌晨一点重新索引数据库中的内容
     * @throws Exception
     */
    @Scheduled(cron = "0 0 1 * * *")
//    @Scheduled(fixedRate = 1000*20)  //每隔20秒触发此方法
    public void taskIndex() throws Exception{
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
            document.add(new Field("content", PatternUtil.filterIndexContent(problem.getContent()), TextField.TYPE_STORED));
            index.addDocument(document);
        }
        index.commit();
    }

}
