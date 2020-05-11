package com.ehi.service.impl;

import com.ehi.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * ClassName: UploadServiceImpl
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 18:47
 * @Description: TODO
 */
@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 简单上传
     *
     * @param file
     */
    @Override
    public void simpleUpload(MultipartFile file) throws IOException {
        File testFile = new File("D:\\test.txt");
        file.transferTo(testFile);
    }

    /**
     * 断点上传
     *
     * @param file
     */
    @Override
    public void breakUpload(MultipartFile file, Long position, Long fileLenght) throws IOException {
        String storePath = "D:\\";
        String filename = file.getOriginalFilename();
        RandomAccessFile accessFile = new RandomAccessFile(storePath + filename,"rw");
        InputStream inputStream = file.getInputStream();
        if (position >= 0) {
            accessFile.seek(position);
        }
        int b= 0;
        while (-1 != (b= inputStream.read())) {
            accessFile.write(b);
        }
    }
}