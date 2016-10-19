package net.begincode.lucene.search;


import net.begincode.core.model.Problem;
import net.begincode.lucene.analyzer.MyIKTokenizer;
import net.begincode.lucene.analyzer.MyIkAnalyzer;
import net.begincode.lucene.bean.SearchResultBean;
import net.begincode.lucene.manager.IndexManager;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Stay on 2016/9/29  12:28.
 */
public class MyIndexSearch {

    private IndexManager indexManager;

    public MyIndexSearch(String indexName) {
        this.indexManager = IndexManager.getIndexManager(indexName);
    }

    /**
     * @param query 查询条件
     * @param start 从第几条 从0 开始奇数 包括start
     * @param end  到第几条 不包括end
     * @param sort 自定义排序方式
     * @return
     * @Description: 分页查询
     */
   /* public SearchResultBean search(Query query, int start, int end, Sort sort) {
        start = start > 0 ? start : 0;
        end = end > 0 ? end : 0;
        if (query == null || start > end || indexManager == null) {
            return null;
        }
        SearchResultBean bean = new SearchResultBean();
        List<Document> docs = new ArrayList<Document>();
        bean.setDocs(docs);
        IndexSearcher searcher = indexManager.getIndexSearcher();

        try {
            TopDocs topDocs = searcher.search(query, end, sort);
            bean.setSumCount(topDocs.totalHits);
            end = end > topDocs.totalHits ? topDocs.totalHits : end;
            for (int i = start; i < end; i++) {
                docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            indexManager.relase(searcher);
        }

        return bean;
    }*/

    /**
     * @param query 查询条件
     * @param start 从第几条 从0 开始奇数 包括start
     * @param end  到第几条 不包括end
     * @return
     * @Description: 分页查询，排序采用默认的排序方式
     */
    public SearchResultBean search(Query query, int start, int end) {
        start = start > 0 ? start : 0;
        end = end > 0 ? end : 0;
        if (query == null || start > end || indexManager == null) {
            return null;
        }
        SearchResultBean bean = new SearchResultBean();
        List<Problem> list = new ArrayList<Problem>();
        IndexSearcher searcher = indexManager.getIndexSearcher();
        try {
            TopDocs topDocs = searcher.search(query, end);
            bean.setSumCount(topDocs.totalHits);
            end = end > topDocs.totalHits ? topDocs.totalHits : end;
            QueryScorer scorer = new QueryScorer(query);// 查询得分
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);// 得到得分的片段，就是得到一段包含所查询的关键字的摘要
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");// 对查询的数据格式化；无参构造器的默认是将关键字加粗
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);// 根据得分和格式化
            highlighter.setTextFragmenter(fragmenter);// 设置成高亮
            for (int i = start; i < end; i++) {
                Problem problem = new Problem();
                Document doc = searcher.doc(topDocs.scoreDocs[i].doc);
                String title = doc.get("title");
                String content = doc.get("content");
                String id = doc.get("id");
                String solve = doc.get("solve");
                if (content != null && title !=null) {
                    TokenStream contentTokenStream = new MyIkAnalyzer().tokenStream("content", new StringReader(content));// TokenStream将查询出来的搞成片段，得到的是整个内容
                    TokenStream titleTokenStream = new MyIkAnalyzer().tokenStream("title", new StringReader(title));// TokenStream将查询出来的搞成片段，得到的是整个内容
                    String highContent = highlighter.getBestFragment(contentTokenStream,content);
                    if(highContent != null){
                        problem.setContent(highContent);// 将权重高的摘要显示出来，得到的是关键字内容
                    }else{
                        problem.setContent(content);
                    }
                    problem.setTitle(highlighter.getBestFragment(titleTokenStream,title));// 将权重高的摘要显示出来，得到的是关键字内容
                    problem.setProblemId(Integer.parseInt(id));
                    problem.setSolve(Integer.parseInt(solve));
                    list.add(problem);
                }
            }
            bean.setDocs(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            indexManager.relase(searcher);
        }

        return bean;
    }


}
