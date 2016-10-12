package net.begincode.lucene.utils;

import net.begincode.common.BeginCodeConstant;
import net.begincode.core.model.Problem;
import net.begincode.lucene.bean.ConfigBean;
import net.begincode.lucene.index.Index;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;

import java.util.HashSet;

/**
 * Created by Stay on 2016/10/2  19:18.
 */
public class LuceneUtil {



    /**
     * 新建问题添加进索引
     * @param problem
     */
    public static void createIndex(Problem problem){
        Index index = new Index(BeginCodeConstant.INDEX_FILE_NAME);
        Document doc = new Document();
        doc.add(new StringField("id",problem.getProblemId().toString(), Field.Store.YES));
        doc.add(new StringField("solve","0", Field.Store.YES));
        doc.add(new Field("title",problem.getTitle(), TextField.TYPE_STORED));
        doc.add(new Field("content",problem.getContent(), TextField.TYPE_STORED));
        index.addDocument(doc);
    }

    public  static void updateIndex(Problem problem){
        Index index = new Index(BeginCodeConstant.INDEX_FILE_NAME);
        Document doc = new Document();
        doc.add(new StringField("id",problem.getProblemId().toString(), Field.Store.YES));
        doc.add(new Field("title",problem.getTitle(), TextField.TYPE_STORED));
        doc.add(new Field("content",problem.getContent(), TextField.TYPE_STORED));
        index.updateDocument(new Term("id"),doc);
    }


}
