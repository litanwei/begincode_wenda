package net.begincode.controller;

import com.qiniu.util.StringMap;
import net.begincode.common.BizException;
import net.begincode.core.support.AuthPassport;
import net.begincode.enums.CommonResponseEnum;
import net.begincode.utils.QiniuUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by Stay on 2016/11/22  15:40.
 */
@Controller
@RequestMapping(value = "/summernote")
public class SummernoteImgController {

    private Logger logger = LoggerFactory.getLogger(SummernoteImgController.class);

    /**
     * summernote 文件上传 controller
     *
     * @param file
     * @param request
     * @return
     */
    @AuthPassport
    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImgage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        File destFile = null;
        if (!file.isEmpty()) {
            try {
                String path = request.getSession().getServletContext().getRealPath("/upload");
                String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                destFile = new File(path + "/" + fileName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile); //复制临时文件到指定目录
                StringMap stringMap = QiniuUtil.upload(fileName, path + "/" + fileName);
                return stringMap.map();
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new BizException(CommonResponseEnum.EXCEPTION);
            } finally {
                destFile.delete();
            }
        }
        return null;
    }

}
