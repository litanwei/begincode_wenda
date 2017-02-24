package net.begincode.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * Created by Stay on 2016/10/18  15:32.
 */
public class JsoupUtil {
    /**
     * 替换内容中的嵌套的代码 替换成{代码...}
     * @param content
     * @return
     */
    public static String replaceContent(String content){
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("code").empty().append("{代码}...");
        String text = doc.body().text().replace("?","");
        return text;
    }

    /**
     * 匹配所有的a标签 过滤出a标签@后面的名字  组成一个不重复的set集合
     *
     * @param content
     * @return
     */
    public static Set<String> matchMessageName(String content){
        HashSet<String> hashSet = new HashSet<>();
        Document doc = Jsoup.parse(content);
        Elements element = doc.getElementsByClass("message_");
        for(Element e : element){
            hashSet.add(e.text().replace("@",""));
        }
        return hashSet;
    }


    /**
     * 查找summernote 里面@人的href链接  返回用户id  set集合
     *
     * @param content
     * @return
     */
    public static Set<Integer> matchMessageUserId(String content){
        HashSet<Integer> hashSet = new HashSet<>();
        Document doc = Jsoup.parse(content);
        Elements element = doc.getElementsByClass("message_").select("a");
        String[] attrChar = null;
        for(Element e : element){
            attrChar = e.attr("href").replace(".","/").split("/");
            hashSet.add(Integer.parseInt(attrChar[attrChar.length-2]));
        }
        return hashSet;
    }

}
