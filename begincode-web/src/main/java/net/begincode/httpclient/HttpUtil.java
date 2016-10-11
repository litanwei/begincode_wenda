package net.begincode.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stay on 2016/10/7  17:29.
 */
public class HttpUtil {



    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);




    /**
     * 发送http请求给lucene索引库新增索引
     * @param problemId
     */
    public static void createIndexHttp(Integer problemId) {
        try {
            HttpClientConfig httpClientConfig = new HttpClientConfig();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //创建一个post对象
            HttpPost post = new HttpPost(httpClientConfig.getUrl() +":"+httpClientConfig.getPort()+"/"+httpClientConfig.getControllerPath()+"/createReceive.htm");

            List<NameValuePair> list = new ArrayList<NameValuePair>();

            list.add(new BasicNameValuePair("problemId", problemId.toString()));

            StringEntity entity = new UrlEncodedFormEntity(list);
            //设置请求的内容
            post.setEntity(entity);

            //执行post请求
            CloseableHttpResponse response = httpClient.execute(post);
            response.close();
            httpClient.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
