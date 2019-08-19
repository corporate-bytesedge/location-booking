package com.bytesedge.bookvenue.controller.fileUpload;

import org.springframework.web.multipart.MultipartFile;
 
public class FileBucket {
 
    MultipartFile file;
     
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}