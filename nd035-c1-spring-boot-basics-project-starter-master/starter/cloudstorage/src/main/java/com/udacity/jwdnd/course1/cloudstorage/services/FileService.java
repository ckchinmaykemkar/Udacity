package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public String[] getFileListings(Integer userid){
        return fileMapper.getFileListings(userid);
    }
    public void addFile(MultipartFile multipartFile, String userName)throws IOException{
        InputStream fis=multipartFile.getInputStream();
        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
        int nRead;
        byte[] data=new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] filedata= buffer.toByteArray();
        String fileName=multipartFile.getOriginalFilename();
        String contentType=multipartFile.getContentType();
        String fileSize=String.valueOf(multipartFile.getSize());
        Integer userid=userMapper.getUsers(userName).getUserid();
        File file=new File(0,fileName,contentType,fileSize,userid,filedata);
        fileMapper.insert(file);

    }
    public File getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }
}
