
package com.omar.restaurant.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.omar.restaurant.services.PhotoService;
import com.omar.restaurant.domain.entities.Photo;
import com.omar.restaurant.domain.entities.Restaurant;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.Optional;
import java.util.UUID;

import com.omar.restaurant.services.StorageService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PhotoServiceImpl  implements PhotoService{

    private final StorageService storageService;

    @Override
    public Photo uploadPhoto(MultipartFile file) {
        String photoId =UUID.randomUUID().toString();
        String url = storageService.store(file,photoId);

        return Photo.builder()
         .url(url)
         .uploadDate(LocalDateTime.now())
         .build();
        
    }
    @Override
    public Optional<Resource> getPhotoAsResource(String id) {
        return storageService.loadAsResource(id);
    }

}
