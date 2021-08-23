package com.project.real_estate_1.storage;

import com.project.real_estate_1.entity.ImgNumCount;
import com.project.real_estate_1.service.ImgCntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;
    @Autowired
    private ImgCntService imgCntService;

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file){
        UUID uuid = UUID.randomUUID();
        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }

            String newImgName = (imgCntService.countUp()).toString() + ".png";
            if(newImgName.equals("1.png")) initialization();
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(newImgName));
            Files.copy(file.getInputStream(), this.rootLocation.resolve(uuid.toString() + "_" + file.getOriginalFilename()));
            return newImgName;
        } catch (IOException e){
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
    @Override
    public Stream<Path> loadAll(){
        try{
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e){
            throw new StorageException("Failed to read stored files", e);
        }
    }

    private void initialization() {
        try{
            Files.createDirectory(rootLocation);
        } catch (IOException e){
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
            try{
                Path file = load(filename);
                Resource resource = new UrlResource(file.toUri());
                if(resource.exists() || resource.isReadable()){
                    return resource;
                }
                else{
                    throw new StorageFileNotFoundException("Could not read file: " + filename);
                }
            } catch(MalformedURLException e){
                throw new StorageException("Could not read file: " + filename, e);
            }
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }
}
