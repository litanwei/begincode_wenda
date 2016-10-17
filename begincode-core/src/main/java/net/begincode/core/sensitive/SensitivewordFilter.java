package net.begincode.core.sensitive;

import org.springframework.stereotype.Component;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by saber on 2016/10/15.
 */

@Component
public class SensitivewordFilter {

    public Map sensitiveWordMap = null;

    /**
     * 为敏感字库设置软引用，内存不足时清理。
     */
    public void readSensitiveWord(){
        sensitiveWordMap = new ForbiddenWordsInit().initChangeWord();
        SoftReference softSensitiveWord = new SoftReference(sensitiveWordMap);
        sensitiveWordMap = null;
        if(softSensitiveWord.get()!=null){
            sensitiveWordMap = (Map)softSensitiveWord.get();
            System.out.println("不用装载");
        }else{
            sensitiveWordMap = new ForbiddenWordsInit().initChangeWord();
            softSensitiveWord = new SoftReference(sensitiveWordMap);
            sensitiveWordMap =null;
            System.out.println("装载");
        }
    }


    /**
     * 判断文字是否包含敏感字符
     * @param txt  需要验证文字
     * @return 若包含敏感字返回替换后的文本txt，否则返回传进来的文本txt
     */
    public String isContaintSensitiveWord(String txt){
        for(int i = 0 ; i < txt.length() ; i++){
            int matchFlag = this.CheckSensitiveWord(txt,i); //判断是否包含敏感字符
            if(matchFlag > 0){    //大于0存在，返回true
                return replaceSensitiveWord(txt);
            }
        }
        return txt;
    }

    /**
     * 获取文字中的敏感词
     * @param txt 文字
     * @return Set<String> 文字中的敏感字
     */
    private Set<String> getSensitiveWord(String txt){
        Set<String> sensitiveWordList = new HashSet<String>();
        for(int i = 0 ; i < txt.length() ; i++){
            int length = CheckSensitiveWord(txt,i);    //判断是否包含敏感字符
            if(length > 0){    //存在,加入list中
                sensitiveWordList.add(txt.substring(i, i+length));
                i = i + length - 1;    //减1的原因，是因为for会自增
            }
        }
        return sensitiveWordList;
    }

    /**
     * 替换敏感字字符
     * @param txt 输入文本
     * @return String 屏蔽敏感字后的文本
     */
    private String replaceSensitiveWord(String txt){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt);     //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars("*", word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }

        return resultTxt;
    }

    /**
     * 获取替换字符串
     * @param replaceChar 替换敏感字的String
     * @param length 敏感字长度
     * @return String 替换后敏感字
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }

        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符
     * @param txt
     * @param beginIndex
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     */
    private int CheckSensitiveWord(String txt,int beginIndex){
        boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
        char word = 0;
        int matchFlag = 0;
        Map nowMap = sensitiveWordMap;
        for(int i = beginIndex; i < txt.length() ; i++){
            word = txt.charAt(i);
            nowMap = (Map) nowMap.get(word);     //获取指定key
            if(nowMap != null){     //存在，则判断是否为最后一个
                matchFlag++;
                if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    flag = true;       //结束标志位为true
                }
            }
            else{     //不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }
}