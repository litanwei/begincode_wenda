package net.begincode.bean;

import java.util.List;

import net.begincode.bean.PageParam;
import net.begincode.core.model.Problem;
import org.apache.lucene.document.Document;

/**
 * Created by Stay on 2016/9/29  12:29.
 */
public class SearchResultBean extends PageParam {
    private int sumCount;
    private List<Problem> docs; //查询的结构数

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public List<Problem> getDocs() {
        return docs;
    }

    public void setDocs(List<Problem> docs) {
        this.docs = docs;
    }

}
