package com.metamorphosis.login.services;

import com.github.dozermapper.core.Mapper;
import com.metamorphosis.login.entities.ImageEntity;
import com.metamorphosis.login.models.ImageModel;
import com.metamorphosis.login.repositories.ImageRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class LandingPageService {

    private ImageRepository imageRepository;
    private Mapper mapper;

    @Autowired
    public LandingPageService(ImageRepository imageRepository, Mapper mapper){
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    public List<ImageEntity> getById(Long id) {
        return imageRepository.findAllByUserId(id);
    }

    public void save(ImageModel imageModel){
        ImageEntity imageEntity = mapper.map(imageModel, ImageEntity.class);
        ImageEntity imageEntity1 = imageRepository.save(imageEntity);
        return;
    }
}
