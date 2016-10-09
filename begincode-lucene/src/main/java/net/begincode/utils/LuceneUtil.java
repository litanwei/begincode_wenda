package net.begincode.utils;

import net.begincode.core.model.Problem;
import net.begincode.index.Index;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

/**
 * Created by Stay on 2016/10/7  15:52.
 */
public class LuceneUtil {
    /**
     * 新建问题添加进索引
     *
     * @param problem
     */
    public static void createIndex(Problem problem) {
        Index index = new Index("/test");
        Document doc = new Document();
        doc.add(new StringField("id", problem.getProblemId().toString(), Field.Store.YES));
        doc.add(new StringField("solve", "0", Field.Store.YES));
        doc.add(new Field("title", problem.getTitle(), TextField.TYPE_STORED));
        doc.add(new Field("content", problem.getContent(), TextField.TYPE_STORED));
        index.addDocument(doc);
    }
}
