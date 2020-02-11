package com.metamorphosis.login.controllers;

import com.metamorphosis.login.entities.ImageEntity;
import com.metamorphosis.login.models.ImageModel;
import com.metamorphosis.login.repositories.ImageRepository;
import com.metamorphosis.login.services.FileUploadService;
import com.metamorphosis.login.services.LandingPageService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class LandingPageController {

    private FileUploadService fileUploadService;
    private LandingPageService landingPageService;

    @Autowired
    public LandingPageController(FileUploadService fileUploadService, LandingPageService landingPageService) {
        this.fileUploadService = fileUploadService;
        this.landingPageService = landingPageService;
    }

    @PostMapping(value = "/upload/file")
    public ResponseEntity uploadTransactionReceipt(@RequestBody MultipartFile file) throws FileUploadException {
        System.out.println(file);
        return new ResponseEntity(fileUploadService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping(value = "/userid/{userId}")
    public ResponseEntity getAll(@PathVariable(value = "userId") Long userId) {
        List<ImageEntity> images;
        images = this.landingPageService.getById(userId);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PostMapping(value = "/write")
    public ResponseEntity writeImageToDb(@RequestBody ImageModel imageModel) {
        landingPageService.save(imageModel);
        Map<String, String> message = new HashMap<String, String>();
        message.put("message", "Image creation successful");
        return new ResponseEntity<Map<String, String>>(message, HttpStatus.CREATED);
    }
}