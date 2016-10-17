package net.begincode.core.sensitive;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Created by saber on 2016/10/15.
 */
@Component
public class ForbiddenWordsInit {

    private HashMap sensitiveWordMap;

    public ForbiddenWordsInit(){
        super();
    }

    /**
     * 转换敏感字库
     *
     * */
    public Map initChangeWord(){
        try {
            Set<String> stringSet = readSensitiveWordFile();
            addSensitiveWordToHashMap(stringSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensitiveWordMap;
    }

    /**
     * 将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = {
     *      isEnd = 0
     *      国 = {<br>
     *      	 isEnd = 1
     *           人 = {isEnd = 0
     *                民 = {isEnd = 1}
     *                }
     *           男  = {
     *           	   isEnd = 0
     *           		人 = {
     *           			 isEnd = 1
     *           			}
     *           	}
     *           }
     *      }
     * @param keyWordSet  敏感词库
     */
    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }
                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    /**
     * 读取敏感词库中的内容，将内容添加到set集合中
     * @throws Exception
     */
    public Set<String> readSensitiveWordFile() throws IOException {
        FileInputStream fis=new FileInputStream("F:/workspace/begincode_wenda/begincode-core/src/main/resources/file/sensitive/forbiddenWords.txt");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        Set<String> stringSet = new HashSet<String>();
        while ((str = br.readLine())!=null) {
            stringSet.add(str);
        }
        br.close();
        isr.close();
        fis.close();
        return stringSet;

    }

    /**
     * 写入敏感词库中的内容，将内容添加到set集合中
     * @throws Exception
     */
    public void writeSensitiveWordFile() throws IOException {
        String[] arrs={
                "敏一感字一",
                "敏二感字二",
                "敏三感字三",
                "敏四感字四",
                "敏五感字五"
        };
        FileOutputStream fos=new FileOutputStream(new File("F:/workspace/begincode_wenda/begincode-core/src/main/resources/file/sensitive/forbiddenWords.txt"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        for(String arr:arrs){
            bw.write(arr+"\t\n");
        }
        bw.close();
        osw.close();
        fos.close();

    }
}


