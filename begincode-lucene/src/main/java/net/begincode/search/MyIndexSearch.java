package net.begincode.search;


import net.begincode.analyzer.MyIkAnalyzer;
import net.begincode.bean.SearchResultBean;
import net.begincode.core.model.Problem;
import net.begincode.manager.IndexManager;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stay on 2016/9/29  12:28.
 */
public class MyIndexSearch {

    private Logger logger = LoggerFactory.getLogger(MyIndexSearch.class);
    private IndexManager indexManager;
    private Analyzer analyzer = new MyIkAnalyzer();

    public MyIndexSearch(String indexName) {
        this.indexManager = IndexManager.getIndexManager(indexName);
    }


    /**
     * 分页查询，排序采用默认的排序方式
     *
     * @param query 查询条件
     * @param start 从第几条 从0 开始奇数 包括start
     * @param end   到第几条 不包括end
     * @return
     */
    public SearchResultBean search(Query query, int start, int end) {
        start = start > 0 ? start : 0;
        end = end > 0 ? end : 0;
        if (query == null || start > end || indexManager == null) {
            return null;
        }
        SearchResultBean bean = new SearchResultBean();
        IndexSearcher searcher = indexManager.getIndexSearcher();
        try {
            TopDocs topDocs = searcher.search(query, end);
            bean.setSumCount(topDocs.totalHits);
            bean.setDocs(highHtml(start, end, query, topDocs, searcher, 100));
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            indexManager.relase(searcher);
        }
        return bean;
    }

    /**
     * 返回带有高亮代码的list集合
     *
     * @param start
     * @param end
     * @param query
     * @param topDocs
     * @param indexSearcher
     * @param subNum        内容截取的字符数个数
     * @return
     */
    private List<Problem> highHtml(int start, int end, Query query, TopDocs topDocs, IndexSearcher indexSearcher, int subNum) {
        List<Problem> list = new ArrayList<>();
        try {
            end = end > topDocs.totalHits ? topDocs.totalHits : end;
            QueryScorer scorer = new QueryScorer(query);// 查询得分
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);// 得到得分的片段，就是得到一段包含所查询的关键字的摘要
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");// 对查询的数据格式化；无参构造器的默认是将关键字加粗
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);// 根据得分和格式化
            highlighter.setTextFragmenter(fragmenter);// 设置成高亮
            for (int i = start; i < end; i++) {
                Problem problem = new Problem();
                Document doc = indexSearcher.doc(topDocs.scoreDocs[i].doc);
                String title = doc.get("title");
                String content = doc.get("content");
                String id = doc.get("id");
                String solve = doc.get("solve");
                if (content != null && title != null) {
                    TokenStream contentTokenStream = new MyIkAnalyzer().tokenStream("content", new StringReader(content));// TokenStream将查询出来的搞成片段，得到的是整个内容
                    TokenStream titleTokenStream = new MyIkAnalyzer().tokenStream("title", new StringReader(title));// TokenStream将查询出来的搞成片段，得到的是整个内容
                    String highContent = highlighter.getBestFragment(contentTokenStream, content);
                    //如果内容的字数小于subNum则只截取其内容
                    if (highContent != null) {
                        if (subNum < highContent.length()) {
                            problem.setContent(highContent.substring(0, subNum) + "...");// 将权重高的摘要显示出来，得到的是关键字内容
                        } else {
                            problem.setContent(content + "...");
                        }
                    } else {
                        problem.setContent(content + "...");
                    }
                    String highTitle = highlighter.getBestFragment(titleTokenStream, title);// 将权重高的摘要显示出来，得到的是关键字内容
                    if (highTitle == null) {
                        problem.setTitle(title);
                    } else {
                        problem.setTitle(highTitle);
                    }
                    problem.setProblemId(Integer.parseInt(id));
                    problem.setSolve(Integer.parseInt(solve));
                    list.add(problem);
                }
            }
            return list;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


}
