package net.begincode.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import net.begincode.common.BizException;
import net.begincode.enums.CommonResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Stay on 2016/11/23  15:26.
 */
public class QiniuUtil {
    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "2liIyhpQX6QXv-tFS6HpWGWnAD6J4M8bRKOaDMKv";
    private static final String SECRET_KEY = "AvTcIsqLYm_zZomEFqkRul3vgrwUiZyKWmk1-lG3";
    //要上传的空间
    private static final String bucketname = "begincode";
    //密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static final Zone z = Zone.autoZone();
    private static final Configuration c = new Configuration(z);

    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    /**
     * 七牛云上传文件
     *
     * @param fileName 上传后的文件名
     * @param filePath 上传的文件名路径
     * @return
     * @throws IOException
     */
    public static StringMap upload(String fileName, String filePath) throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, fileName, getUpToken());
            //打印返回的信息
            return res.jsonToMap();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            throw new BizException(CommonResponseEnum.EXCEPTION);
        }
    }
}
