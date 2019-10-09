package com.java.excel;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * 七牛上传文件服务
 *
 * @author w
 */
//@Component
public class QiNiuFileUtil {

//    private static final Logger logger = LoggerFactory.getLogger(QiNiuFileUtil.class);

    /**
     * 构造一个带指定Zone对象的配置类
     */
    private static Configuration cfg = new Configuration(Zone.autoZone());
    /**
     * 上传处理器
     */
    private static UploadManager uploadManager = new UploadManager(cfg);

//    @Autowired
//    QiNiuConfig qiNiuConfig;

    /**
     * 七牛文件上传工具方法
     * @param inputStream 文件流
     * @param filePath 文件上传目录
     * @param fileName 文件名
     * @return 文件url, 上传失败返回null
     */
    public static String upload(InputStream inputStream, String filePath, String fileName) {

        String bucket = "yysh";
        String baseUrl = "http://oceea34nq.bkt.clouddn.com/";
        String accessKey = "9NNAGFtNQ7SO6CblYkrAOqyPC826EDk3ztxr6UIx";
        String secretKey = "gNdUxsXihkCHfNW2Y8AR3cStyK3wuN7vrdVsr7ra";

        try {

            QiNiuFileUtil.upload(accessKey, secretKey, bucket, inputStream, filePath, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return getAccessUrl(accessKey, secretKey, baseUrl + fileName);
    }

    /**
     * @param accessKey accessKey
     * @param secretKey secretKey
     * @param bucket    存储空间 私有存储空间时, 需要使用token下载, 公用时, 可以匿名下载
     * @param is        流
     * @param filePath  文件存储路径
     * @param fileName  文件名
     */
    private static void upload(String accessKey, String secretKey, String bucket, InputStream is, String filePath, String fileName) throws QiniuException {

        Assert.hasLength(accessKey, "accessKey不能为空");
        Assert.hasLength(secretKey, "secretKey不能为空");
        Assert.hasLength(bucket, "bucket不能为空");

        String fileDirectory;
        if(StringUtils.isEmpty(filePath)){
            fileDirectory = fileName;

        }else{
            fileDirectory = (filePath.endsWith("/") ? filePath : filePath + "/") + fileName;
        }

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        uploadManager.put(is, fileDirectory, upToken, null, null);
    }

    /**
     * 私有空间返回文件URL
     * @param accessKey accessKey
     * @param secretKey secretKey
     * @param baseUrl 基础链接
     * @return 私有文件下载地址
     */
    private static String getAccessUrl(String accessKey, String secretKey, String baseUrl) {

        if(StringUtils.isEmpty(baseUrl)){
//            logger.error("文件路径不能为空！");
            return "";
        }

        if(!(baseUrl.startsWith("https://") || baseUrl.startsWith("http://"))){
            return baseUrl;
        }
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(baseUrl);
    }
}
