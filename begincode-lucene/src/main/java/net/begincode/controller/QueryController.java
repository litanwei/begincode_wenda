package net.begincode.controller;

import net.begincode.analyzer.MyIkAnalyzer;
import net.begincode.bean.Page;
import net.begincode.bean.SearchResultBean;
import net.begincode.common.BeginCodeConstant;
import net.begincode.core.model.Problem;
import net.begincode.search.MyIndexSearch;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Stay on 2016/10/6  22:37.
 */
@Controller
public class QueryController {

    /**
     *  查询入口
     * @param keyword
     * @param p
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String query(@RequestParam(value = "q") String keyword, @RequestParam(value = "p", defaultValue = "1") int p, Model model) throws Exception {
        keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
        if (keyword.length()==0) {
            return "search";
        }
        Page<Problem> page = new Page<>();
        MyIndexSearch myindexSearch = new MyIndexSearch("/test");
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

    /**
     * 分页逻辑代码
     * @param totalPage
     * @param currentNum
     * @param keyword
     * @return
     */
    private String paginationHtml(int totalPage, int currentNum, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul class='pagination'><li><a href='/search.htm?q=" + keyword + "'>首页</a></li>");
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
    }


}
