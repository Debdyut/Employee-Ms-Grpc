package com.employee.app.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.service.IStorageService;

@RestController
public class StorageController {
	
	private final IStorageService storageService;
	
	private final StringEncryptor jasyptStringEncryptor;

    public StorageController(final IStorageService storageService, final StringEncryptor jasyptStringEncryptor) {
        this.storageService = storageService;
        this.jasyptStringEncryptor = jasyptStringEncryptor;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> listAllFiles() {

        List<String> res = storageService.loadAll().map(Path::toString)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {

        String fileContent = storageService.readFile(filename);
        
        fileContent = jasyptStringEncryptor.encrypt(fileContent);
        
        return ResponseEntity.ok().body(fileContent);
    }

}
