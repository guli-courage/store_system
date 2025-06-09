package system.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import system.common.Result;
import system.service.FileService;

@RestController
@RequestMapping("upload")
@CrossOrigin
@Slf4j
public class UploadFileController {

    @Autowired
    private FileService fileService;
    /**
     * 图片文件上传OSS
     * @param file（文件）
     */
    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = fileService.uploadImage(file);
        return Result.SUCCESS(fileUrl);
    }


}