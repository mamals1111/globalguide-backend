package com.myrmicatech.globalguidebackend.util;

import com.myrmicatech.globalguidebackend.entity.Account;
import com.myrmicatech.globalguidebackend.entity.File;
import com.myrmicatech.globalguidebackend.service.file.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FileHandler {

    public boolean isFileEntityExistingInDatabase(File fileToCheck, Account account, List<File> files) {
        for (File file :
                files) {
            if (file.getFileName().equals(fileToCheck.getFileName())
                    && Objects.equals(file.getSize(), fileToCheck.getSize())
                    && Objects.equals(file.getFileType(), fileToCheck.getFileType())
                    && Objects.equals(file.getAccount(), account)) {
                return true;
            }
        }

        return false;
    }
}
