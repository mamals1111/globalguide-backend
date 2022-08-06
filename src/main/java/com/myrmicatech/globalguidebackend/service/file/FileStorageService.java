package com.myrmicatech.globalguidebackend.service.file;

import com.myrmicatech.globalguidebackend.dto.UploadFileDto;
import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.entity.Location;
import com.myrmicatech.globalguidebackend.service.generic.GlobalGuideEntityService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface FileStorageService extends GlobalGuideEntityService<UUID, File> {

    List<File> findAllByLocationBanner(Location location);
    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
    Stream<Path> loadAll();
    File save(UploadFileDto uploadFileDto);
}
