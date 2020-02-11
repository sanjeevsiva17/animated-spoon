package com.metamorphosis.login.services;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.metamorphosis.login.utils.S3UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FileUploadService {
    private S3UploadService s3UploadService;


    @Value("${s3.image.bucket}")
    private String bucketName;

    @Autowired
    public FileUploadService(S3UploadService s3UploadService) {
        this.s3UploadService = s3UploadService;
    }

    public Map<String, String> uploadFile(MultipartFile file) throws FileUploadException {
        try {
            String filepath = System.currentTimeMillis() + "-file-" + file.getOriginalFilename();
            PutObjectResult putObjectResult = s3UploadService.uploadToS3Bucket(filepath, file.getInputStream());
            String s3Url = "https://" + bucketName + ".s3.amazonaws.com/" + filepath;
            Map<String, String> image = new HashMap<String, String>();
            image.put("img", s3Url);
            return image;
        } catch (IOException e) {
            throw new FileUploadException("error uploading file");
        }
    }
}
