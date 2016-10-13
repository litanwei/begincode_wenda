package net.begincode.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Stay on 2016/10/10  12:23.
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

}
