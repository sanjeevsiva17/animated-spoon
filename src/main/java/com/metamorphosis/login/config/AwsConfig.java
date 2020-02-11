package com.metamorphosis.login.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;


@Configuration
public class AwsConfig {


    @Autowired
    Environment environment;

    private String awsAccessKey;

    private String awsSecretKey;

    @Autowired
    public AwsConfig(@Value("${aws_access_key_id}") String awsAccessKey, @Value("${aws_secret_access_key}") String awsSecretKey) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
    }

    @Bean
    public AWSCredentials credential() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean
    @Primary
    public AmazonS3 s3client() {

        credential();
        return new AmazonS3Client(credential());
    }
}
