package net.begincode.core.httpclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Stay on 2016/10/10  16:54.
 */
@Component
public class HttpClientConfig {
//    @Value("#{httpClient['httpClient.url']}")
//    private String url;
    private String url = "http://localhost";
//    @Value("#{httpClient['httpClient.port']}")
//    private Integer port;
    private Integer port = 8081;
//    @Value("#{httpClient['httpClient.controPath']}")
//    private String controllerPath;
    private String controllerPath = "http";

    public HttpClientConfig() {
    }
    public HttpClientConfig(String url,Integer port,String controllerPath){
        this.url = url;
        this.port = port;
        this.controllerPath = controllerPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getControllerPath() {
        return controllerPath;
    }


    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }
}
