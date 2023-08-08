package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Files Service
 */
@Service
    public class FilesService {

    /**
     * Bean {@link FilesMapper}
     */
    @Autowired
    private FilesMapper filesMapper;
    public void addFile(MultipartFile fileUpload, int userid) throws IOException {
        Files file = new Files();
        String originalFilename = fileUpload.getOriginalFilename();
        List<Files> filesList = filesMapper.findByName(originalFilename + '%');

        if(filesList.size() > 0){
            originalFilename = originalFilename.replace(originalFilename, originalFilename + "("+ filesList.size() +")");
        }
        try {
            file.setContentType(fileUpload.getContentType());
            file.setFileData(fileUpload.getBytes());
            file.setFileName(originalFilename);
            file.setFileSize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        filesMapper.insertFile(file, userid);
    }

    public ResponseFile getResponseFile(Files file) {
        String base64 = Base64.getEncoder().encodeToString(file.getFileData());
        String dataURL = "data:" + file.getContentType() + ";base64," + base64;
        return ResponseFile.builder().fileId(file.getFileId()).fileName(file.getFileName()).dataURL(dataURL).build();
    }

    public List<ResponseFile> getAllFiles(int userId) throws Exception {
        List<Files> files = filesMapper.findByUserId(userId);
        if (files == null) {
            throw new Exception();
        }
        return files.stream().map(this::getResponseFile).collect(Collectors.toList());
    }

    public void deleteFile(int fileId) {
        filesMapper.deleteFile(fileId);
    }
}
