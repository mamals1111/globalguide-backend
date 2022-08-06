package com.myrmicatech.globalguidebackend.service.file;

import com.google.common.hash.Hashing;
import com.google.common.io.ByteSource;
import com.myrmicatech.globalguidebackend.dto.UploadFileDto;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.exception.FileStorageException;
import com.myrmicatech.globalguidebackend.exception.MyFileNotFoundException;
import com.myrmicatech.globalguidebackend.mapper.MapStructMapper;
import com.myrmicatech.globalguidebackend.property.FileStorageProperties;
import com.myrmicatech.globalguidebackend.property.TinifyProperties;
import com.myrmicatech.globalguidebackend.repository.FileRepository;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityServiceImpl;
import com.myrmicatech.globalguidebackend.util.FileHandler;
import com.tinify.Source;
import com.tinify.Tinify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl extends GlobalGuideEntityServiceImpl<UUID, File> implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    public FileStorageServiceImpl(FileRepository fileRepository, FileStorageProperties fileStorageProperties, TinifyProperties tinifyProperties) {
        super(fileRepository);
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        System.out.println(fileStorageLocation);
        Tinify.setKey(tinifyProperties.getApikey());

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public List<File> findAllByLocationBanner(Location location) {
        List<File> files = fileRepository.findAllByLocationBanner(location);
        return files;
    }

    public String storeFile(MultipartFile file) {
       
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
           
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            String[] fileNameSplitted = fileName.split("\\.");
            String fileNameWithoutExtension = String.join("", Arrays.copyOf(fileNameSplitted, fileNameSplitted.length - 1));
            String fileNameExtension = fileNameSplitted[fileNameSplitted.length - 1];
            long size = file.getSize();
            String type = file.getContentType();
            String fileNameHashed = Hashing.sha256().hashString(fileNameWithoutExtension + size + type, StandardCharsets.UTF_8).toString();
            String finalFilename = String.join(".", fileNameHashed, fileNameExtension);

           
            boolean isMultipartFileExisting = checkMultipartFileExisting(finalFilename, file);
            if (!isMultipartFileExisting) {
               
                Path targetLocation = this.fileStorageLocation.resolve(finalFilename);
//                Source source = Tinify.fromBuffer(file.getBytes());
//                byte[] resultData = source.toBuffer();
//                InputStream targetStream = ByteSource.wrap(resultData).openStream();
//                Files.copy(targetStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            return finalFilename;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }


    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.fileStorageLocation, 1)
                    .filter(path -> !path.equals(this.fileStorageLocation))
                    .map(this.fileStorageLocation::relativize);
        }
        catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }

    }

    @Override
    public File save(UploadFileDto uploadFileDto) {
        File file = mapStructMapper.uploadFileDtoToFile(uploadFileDto);
        fileRepository.save(file);
        return null;
    }

    public boolean checkMultipartFileExisting(String finalFileName, MultipartFile file) {
        long fileSize = file.getSize();
        String fileType = file.getContentType();
        List<File> filesInDatabase = fileRepository.findAll();
        for (File dbFile :
                filesInDatabase) {
            if (Objects.equals(dbFile.getFileName(), finalFileName)
                    && Objects.equals(dbFile.getSize(), fileSize)
                    && Objects.equals(dbFile.getFileType(), fileType)) {
                return true;
            }
        };

        return false;
    }

}
