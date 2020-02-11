package com.metamorphosis.login.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class S3UploadService {
    private AmazonS3 s3Client;

    private String bucketName;

    @Autowired
    public S3UploadService(AmazonS3 amazonS3, @Value("${s3.image.bucket}") String bucketName) {
        this.s3Client = amazonS3;
        this.bucketName = bucketName;
    }

    public PutObjectResult uploadToS3Bucket(String key, InputStream inputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, byteArrayInputStream, metaData);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = s3Client.putObject(putObjectRequest);
        return putObjectResult;
    }
}
