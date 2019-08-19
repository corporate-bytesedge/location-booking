package com.bytesedge.bookvenue.controller.fileUpload;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
 
@Component
public class FileValidator implements Validator {
     
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;
         
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "file.upload.zero.file", "File size is Zero");
            }
            if (file.getFile().getSize() > 3 * 1024 * 1024) {
                errors.rejectValue("file", "file.upload.large.file", "File size is more than 3 MB");
            }
        }
    }
}