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
     * 过滤内容中@后面的用户名 不允许有重复的名进入 把html标签去掉
     *
     * @param content
     * @return
     */
    public static Set<String> filterNickName(String content) {
        Set<String> set = new HashSet<String>();
        String pt = "@[^\\\\@ ]{1,20}";
        Matcher matcher = Pattern.compile(pt).matcher(content.trim().replaceAll("</?[^>]+>", " ").replace("&nbsp;", " "));
        while (matcher.find()) {
            set.add(matcher.group().trim().replace("@", ""));
        }
        return set;
    }


}
