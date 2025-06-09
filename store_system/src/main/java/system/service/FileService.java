package system.service;

import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileService {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    @Value("${aliyun.oss.url-prefix}")
    private String urlPrefix;

    // 创建OSS客户端
    private OSS createOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 上传图片到OSS
     * @param file 图片文件
     * @return 图片访问URL
     */
    public String uploadImage(MultipartFile file) {
        OSS ossClient = createOssClient();
        try {
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() + fileExtension;

            ossClient.putObject(
                    bucketName,
                    fileName,
                    file.getInputStream()
            );

            return urlPrefix + fileName;
        } catch (IOException e) {
            log.error("OSS文件上传失败", e);
            throw new RuntimeException("文件上传失败");
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 从OSS删除图片
     * @param imageUrl 图片完整URL
     */
    public void deleteImage(String imageUrl) {
        if (StringUtils.isEmpty(imageUrl) || !imageUrl.startsWith(urlPrefix)) {
            return;
        }

        OSS ossClient = createOssClient();
        try {
            // 提取文件名（去掉URL前缀）
            String fileName = imageUrl.substring(urlPrefix.length());
            ossClient.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            log.error("OSS文件删除失败: {}", imageUrl, e);
            throw new RuntimeException("文件删除失败");
        } finally {
            ossClient.shutdown();
        }
    }
}
