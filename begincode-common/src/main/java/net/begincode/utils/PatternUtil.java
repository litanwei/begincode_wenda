package net.begincode.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * Created by Stay on 2016/9/6  12:18.
 */
public class PatternUtil {

    /**
     * 验证传入的名称是否匹配 数字，字母，下划线，中文
     *
     * @param str 传入的字符串
     * @return 符合返回true 不符合返回false
     */
    public static boolean checkStr(String str) {
        String pt = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        Matcher matcher = Pattern.compile(pt).matcher(str.trim());
        return matcher.matches();
    }

    /**
     * 过滤内容中@后面的用户名 不允许有重复的名进入 把html标签去掉  比如: @yang
     * @param content
     * @return
     */
    public static Set<String> filterNickName(String content) {
        Set<String> set = new HashSet<String>();
        String pt = "@[^\\\\@ ]{1,20}";
        Matcher matcher = Pattern.compile(pt).matcher(content.trim().replaceAll("</?[^>]+>", " ").replace("&nbsp;", " "));
        while (matcher.find()) {
            set.add(matcher.group().trim());
        }
        return set;
    }




    /**
     * 以逗号切割传入字符串  里面调用了此工具类的checkStr() 方法
     *
     * @param name 传入的内容
     * @return 返回包含标签名的set集合(不允许重复)
     */
    public static Set<String> splitName(String name) {
        HashSet<String> set = new HashSet<String>();
        //前台传入标签名 这里开始切割 替换中文逗号
        String[] labelNames = name.replace("，", ",").split(",");
        for (int i = 0; i < labelNames.length; i++) {
            set.add(labelNames[i]);
        }
        for (String labelName : set) {
            //判断是否符合只有数字 字母 下划线 中文
            if (checkStr(labelName)) {
                continue;
            } else {
                set.remove(labelName);
            }
        }
        return set;
    }

    /**
     * 内容中过滤html标签
     * @param content
     * @return
     */
    public static String filterIndexContent(String content){
        return content.trim().replaceAll("</?[^>]+>", " ").replace("&nbsp;", " ");
    }


}
