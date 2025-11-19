
package com.omar.restaurant.services;

import com.omar.restaurant.domain.entities.Photo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.util.Optional;


public interface PhotoService {
    
    Photo uploadPhoto(MultipartFile file);
    Optional<Resource> getPhotoAsResource(String id);
}
