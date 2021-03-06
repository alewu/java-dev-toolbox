package com.ale;

import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

import static com.ale.FileSuffixConstant.*;

/**
 * @author alewu
 * @date 2018/2/7
 * @description java使用阿里云OSS存储对象上传图片
 */
public class OssClientUtil {
    private static Logger logger = LoggerFactory.getLogger(OssClientUtil.class);

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        OssClientUtil.setEndpoint(ConfigUtil.getParameter("endpoint"));
        OssClientUtil.setAccessKeyId(ConfigUtil.getParameter("accessKeyId"));
        OssClientUtil.setAccessKeySecret(ConfigUtil.getParameter("accessKeySecret"));
        OssClientUtil.setBucketName(ConfigUtil.getParameter("bucketName"));
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 生成一个预签名的URL
     *
     * @param key 键
     * @return 图片地址
     */
    public static String getImgURl(OSSClient ossClient, String key) {
        //1、设置URL过期时间为10年
        Date expiration = new Date(Instant.now().plusMillis(10 * 365 * 24 * 3600 * 1000L).toEpochMilli());
        // http://rentapp.oss-cn-shenzhen.aliyuncs
        // .com/image/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20171027105906_1511837946200
        // .png?Expires=1513640426&OSSAccessKeyId=LTAI7pMiGYEwhHsA&Signature=QoCqUcIUQzrlnLhJKilHLOdNzGU%3D
        // 2、生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        String urlStr = url.toString();
        return urlStr.substring(0, urlStr.lastIndexOf("?"));
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") - 1);
        if (BMP.equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (GIF.equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (JPEG.equalsIgnoreCase(fileExtension) || JPG.equalsIgnoreCase(fileExtension)
                || PNG.equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (HTML.equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (TXT.equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (VSD.equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (PPT.equalsIgnoreCase(fileExtension)
                || PPTX.equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (DOC.equalsIgnoreCase(fileExtension)
                || DOCX.equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (XML.equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        // 默认返回类型
        return "application/octet-stream";
    }


    public static void main(String[] args) {
        OSSClient oss = getOSSClient();
        oss.putObject(bucketName, "file", new ByteArrayInputStream(new byte[0]));
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        OssClientUtil.endpoint = endpoint;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static void setAccessKeyId(String accessKeyId) {
        OssClientUtil.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssClientUtil.accessKeySecret = accessKeySecret;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public static void setBucketName(String bucketName) {
        OssClientUtil.bucketName = bucketName;
    }
}
