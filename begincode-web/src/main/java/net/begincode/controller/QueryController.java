package net.begincode.controller;

import net.begincode.bean.Page;
import net.begincode.common.BeginCodeConstant;
import net.begincode.core.model.Problem;
import net.begincode.lucene.analyzer.MyIkAnalyzer;
import net.begincode.lucene.bean.SearchResultBean;
import net.begincode.lucene.search.MyIndexSearch;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Stay on 2016/9/28  18:15.
 */

@Controller
@RequestMapping(value = "/")
public class QueryController {


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String query(@RequestParam(value = "q") String keyword, @RequestParam(value = "p", defaultValue = "1") int p, Model model) throws Exception {
        keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        if (keyword.trim() == "") {
            return null;
        }
        Page<Problem> page = new Page<>();
        MyIndexSearch myindexSearch = new MyIndexSearch(BeginCodeConstant.INDEX_FILE_NAME);
        QueryParser queryParser = new MultiFieldQueryParser(new String[]{"title", "content"}, new MyIkAnalyzer());
        Query query = queryParser.parse(keyword);
        SearchResultBean searchResultBean = myindexSearch.search(query, (p - 1) * BeginCodeConstant.PAGESIZE, p * BeginCodeConstant.PAGESIZE);
        page.setTotalNum(searchResultBean.getSumCount());
        List<Problem> problemList = searchResultBean.getDocs();
        page.setCurrentNum(p);
        page.setData(problemList);
        model.addAttribute("totalPage", page.getTotalNum());
        model.addAttribute("searchList", page.getData());
        model.addAttribute("pagination", paginationHtml(page.getTotalPage(), p, keyword));
        return "search";
    }

    private String paginationHtml(int totalPage, int currentNum, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul class='pagination'><li><a href='${ctx}/search.htm?q=" + keyword + "'>首页</a></li>");
        if (currentNum == 1) {
            stringBuilder.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
        } else {
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum - 1) + "'>上一页</a></li>");
        }
        int i = 1;
        if (currentNum > 4) {
            i = currentNum - 2;
        }
        for (; i < totalPage + 1; i++) {
            if (i != currentNum) {
                stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + i + "'>" + i + "</a></li>");
            } else {
                stringBuilder.append("<li class='active'><a href='/search.htm?q=" + keyword + "&p=" + i + "'>" + i + "</a></li>");
            }
        }
        if (currentNum == totalPage) {
            stringBuilder.append("<li class='previous disabled'><a href='#'>下一页</a></li><li><a href='/search.htm?q=" + keyword + "&p=" + totalPage + "'>末页</a></li></ul>");
        } else {
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum + 1) + "'>下一页</a></li><li><a href='/search.htm?q=" + keyword + "&p=" + totalPage + "'>末页</a></li></ul>");
        }
        return stringBuilder.toString();


       /* if (totalPage == 1) {
            stringBuilder.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
            stringBuilder.append("<li class='active'><a href='${ctx}/search.htm?q=" + keyword + "&p=1'>1</a></li>");
            stringBuilder.append("<li class='previous disabled'><a href='#'>下一页</a></li>");
            stringBuilder.append("<li><a href='${ctx}/search.htm?q=" + keyword + "'>末页</a></li></ul>");
            return stringBuilder.toString();
        }
        if (totalPage < 6 && currentNum == 1) {
            stringBuilder.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
            stringBuilder.append("<li class='active'><a href='/search.htm?q=" + keyword + "&p=1'>1</a></li>");
            for (int i = 1; i < totalPage; i++) {
                stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (i + 1) + "'>" + (i + 1) + "</a></li>");
            }
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum + 1) + "'>下一页</a></li>");
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + totalPage + "'>末页</a></li></ul>");
            return stringBuilder.toString();
        } else if (totalPage < 6 && currentNum > 1) {
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum - 1) + "'>上一页</a></li>");
            for (int i = 1; i < totalPage; i++) {
                if (i + 1 == currentNum) {
                    stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" +i + "'>" + i + "</a></li>");
                    stringBuilder.append("<li class='active'><a href='${ctx}/search.htm?q=" + keyword + "&p=" + currentNum + "'>" + currentNum + "</a></li>");
                } else {
                    stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (i + 1) + "'>" + (i + 1) + "</a></li>");
                }
            }
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum + 1) + "'>下一页</a></li>");
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + totalPage + "'>末页</a></li></ul>");
            return stringBuilder.toString();
        }
        if(totalPage>6 && currentNum == 1){
            stringBuilder.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
            stringBuilder.append("<li class='active'><a href='/search.htm?q=" + keyword + "&p=1'>1</a></li>");
            for(int i=1;i<5;i++){
                stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" +(i+1)+ "'>" + (i+1) + "</a></li>");
            }
            stringBuilder.append("<li class='disabled'><a>...</a></li>");
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + (currentNum + 1) + "'>下一页</a></li>");
            stringBuilder.append("<li><a href='/search.htm?q=" + keyword + "&p=" + totalPage + "'>末页</a></li></ul>");
            return stringBuilder.toString();
        }*/


        /*else{
            stringBuilder.append(" <li><a href='${ctx}/search.htm?q="+keyword+"&p="+(currentNum-1)+"'>上一页</a></li>");
        }
        if(totalPage < 5){
            for(int i= 0;i<totalPage;i++){
                if(i!=currentNum)
                {
                    stringBuilder.append("<li><a href='${ctx}/search.htm?q="+keyword+"&p="+(i+1)+"'>"+(i+1)+"</a></li>");
                }else{
                    stringBuilder.append("<li class='ative'><a href='${ctx}/search.htm?q="+keyword+"&p="+(currentNum)+"'>"+currentNum+"</a></li>");
                }
            }
        }else{

        }*/
    }


}
