package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.exceptions.FileNotFoundException;
import ru.itis.models.FileInfo;
import ru.itis.repositories.FileInfoRepository;
import ru.itis.services.FilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

    @Value("${files.storage.path}")
    private String storagePath;

    private final FileInfoRepository fileInfoRepository;

    @Transactional
    @Override
    public FileInfo upload(MultipartFile file) {
        if (file == null){
            return null;
        }

        String extension = file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf("."));

        String storageFileName = UUID.randomUUID() + extension;

        FileInfo fileInfo = FileInfo.builder()
                .type(file.getContentType())
                .originalFileName(file.getOriginalFilename())
                .storageFileName(storageFileName)
                .size(file.getSize())
                .build();

        try{
            Files.copy(file.getInputStream(), Paths.get(storagePath, fileInfo.getStorageFileName()));
        }catch (IOException e){
            throw new IllegalArgumentException("Can not handle file", e);
        }

        return fileInfoRepository.save(fileInfo);
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoRepository.findByStorageFileName(fileName)
                .orElseThrow(FileNotFoundException::new);

        response.setContentLength(fileInfo.getSize().intValue());
        response.setContentType(fileInfo.getType());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");

        try{
            IOUtils.copy(new FileInputStream(
                    storagePath + "\\" + fileInfo.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
