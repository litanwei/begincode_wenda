package net.begincode.bean;

import net.begincode.analyzer.MyIkAnalyzer;
import org.apache.lucene.analysis.Analyzer;

/**
 * Created by Stay on 2016/9/28  15:52.
 */
public class ConfigBean {
//    @Value("${lucene.dirName}")
    private String indexName = "/test";//索引名
//    @Value("${lucene.path}")
    private String indexPath = "d:/begincodeIndex";//索引硬盘路径
    private Analyzer analyzer = new MyIkAnalyzer();//索引分词器
    private double indexReopenMaxStaleSec = 10;
    private double indexReopenMinStaleSec = 0.025;
    private int indexCommitSeconds = 30;//索引写入磁盘时间间隔

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public double getIndexReopenMaxStaleSec() {
        return indexReopenMaxStaleSec;
    }

    public void setIndexReopenMaxStaleSec(double indexReopenMaxStaleSec) {
        this.indexReopenMaxStaleSec = indexReopenMaxStaleSec;
    }

    public double getIndexReopenMinStaleSec() {
        return indexReopenMinStaleSec;
    }

    public void setIndexReopenMinStaleSec(double indexReopenMinStaleSec) {
        this.indexReopenMinStaleSec = indexReopenMinStaleSec;
    }

    public int getIndexCommitSeconds() {
        return indexCommitSeconds;
    }

    public void setIndexCommitSeconds(int indexCommitSeconds) {
        this.indexCommitSeconds = indexCommitSeconds;
    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }
}
