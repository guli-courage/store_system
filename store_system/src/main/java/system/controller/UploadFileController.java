package system.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import system.common.Result;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@CrossOrigin
@Slf4j
public class UploadFileController {
    @Value("${aliyun.oss.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;


    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        //完成图片的上传
        try {
            String oldName = file.getOriginalFilename(); //"a.jpg"
            String suffix = oldName.substring(oldName.lastIndexOf("."));//".jpg"
            String newName= UUID.randomUUID()+""+suffix;
            //2. 上传图片到阿里云的OSS服务
            //创建oss的客户端对象
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            PutObjectResult result = ossClient.putObject(bucketName, newName, file.getInputStream());
            String date ="https://"+bucketName+"."+endPoint+"/"+newName;
            //3. 响应结果
            return Result.SUCCESS(date);
        } catch (IOException e) {
            return Result.FAIL();
        }
    }
}