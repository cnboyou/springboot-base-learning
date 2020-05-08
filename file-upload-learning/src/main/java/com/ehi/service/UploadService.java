package com.ehi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * ClassName: UploadService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/8 18:47
 * @Description: TODO
 */
public interface UploadService {
    /**
     * 简单上传
     * @param file
     */
    void simpleUpload(MultipartFile file) throws IOException;

    /**
     * 断点上传
     * @param file
     */
    void breakUpload(MultipartFile file, Long position, Long fileLenght);
}