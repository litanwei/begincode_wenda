package net.begincode.lucene.index;

import net.begincode.lucene.manager.IndexManager;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TrackingIndexWriter;
import org.apache.lucene.search.Query;

/**
 * Created by Stay on 2016/9/28  17:39.
 */
public class Index {
    private TrackingIndexWriter trackingIndexWriter;
    private String indexName;

    public Index(String indexName) {
        this.indexName = indexName;
        this.trackingIndexWriter = IndexManager.getIndexManager(indexName).getTrackingIndexWriter();
    }

    /**
     * 向索引添加一条记录
     * @param doc
     * @return
     */
    public boolean addDocument(Document doc) {
        try {
            trackingIndexWriter.addDocument(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除符合条件的记录
     * @param query
     * @return
     */
    public boolean deleteDocument(Query query) {
        try {
            trackingIndexWriter.deleteDocuments(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 清空索引中的记录
     * @return
     */
    public boolean deleteAll() {
        try {
            trackingIndexWriter.deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新索引中的记录
     * @param term
     * @param doc
     * @return
     */
    public boolean updateDocument(Term term, Document doc) {
        try {
            trackingIndexWriter.updateDocument(term, doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将内存中的内容提交到磁盘
     */
    public void commit() {
        IndexManager.getIndexManager(indexName).commit();
    }
}
