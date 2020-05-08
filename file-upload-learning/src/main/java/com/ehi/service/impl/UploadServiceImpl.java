package com.ehi.service.impl;

import com.ehi.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public void breakUpload(MultipartFile file, Long position, Long fileLenght) {

    }
}