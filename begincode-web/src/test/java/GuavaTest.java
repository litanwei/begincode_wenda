import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangsj on 2016/12/10.
 */
public class GuavaTest {
    public static void main(String[] args) {
        /**
         * 将字符串 str 根据# 拆分成数组 begin code test 空内容过滤
         * */
        String str = "begin#code##test";
        List<String> javaList = javaDemo(str);
        for(String s : javaList){
            System.out.println(s);
        }
        javaList = guavaDemo(str);
        for(String s : javaList){
            System.out.println(s);
        }
    }

    public static List<String> javaDemo(String str) {
        List list = new ArrayList();
        String[] strArray = str.split("#");
        for (String s : strArray) {
            if (!s.equals("")) {
                list.add(s);
            }
        }
        return list;
    }
    public static List<String> guavaDemo(String str){
       Iterator<String> iterators =  Splitter.on("#").omitEmptyStrings().split(str).iterator();
       return Lists.newArrayList(iterators);
    }
}
