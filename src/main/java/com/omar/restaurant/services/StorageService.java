
package com.omar.restaurant.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import org.springframework.core.io.Resource;


public interface StorageService {
    String store(MultipartFile file, String filename);
    Optional<Resource> loadAsResource(String id);


}
