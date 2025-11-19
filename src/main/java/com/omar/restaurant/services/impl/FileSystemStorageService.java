
package com.omar.restaurant.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.omar.restaurant.services.StorageService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.annotation.PostConstruct;

import java.nio.file.StandardCopyOption;

import org.springframework.core.io.UrlResource;

import com.omar.restaurant.exceptions.StorageException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    private static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);
    @Value("${app.storage.location:uploads}")
    private String storageLocation;
    
    private Path rootLocation;

    @PostConstruct
    public void init(){
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }

    }

    @Override
    public String store(MultipartFile file, String filename) {
        if(file.isEmpty()){
            throw new StorageException("Cannot save an empty file");
        }
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String finalFileName = filename + "." + extension;

        Path destinationFile = rootLocation
                 .resolve(Paths.get(finalFileName))
                 .normalize()
                 .toAbsolutePath();


         if(!destinationFile.getParent().equals(rootLocation.toAbsolutePath())){    
           throw new StorageException("Cannot store file outside current directory");
      }
      try(InputStream inputStream = file.getInputStream()) { 
        Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
        return finalFileName;
      } catch (IOException e) {
        throw new StorageException("Failed to store file", e);
      }
        
    }

    @Override
    public Optional<Resource> loadAsResource(String filename) {

        try {
        Path file = rootLocation.resolve(filename).normalize();

        Resource resource = new UrlResource(file.toUri());

        if(resource.exists() || resource.isReadable()){
            return Optional.of(resource);
        }else{
            return Optional.empty();
        }      

        } catch (MalformedURLException e) {
            log.warn("Could not read file: {}", filename, e);
            return Optional.empty();
            
        }

    }

}
