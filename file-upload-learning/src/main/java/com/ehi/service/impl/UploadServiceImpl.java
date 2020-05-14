package com.ehi.service.impl;

import com.ehi.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
     * 断点上传 实现方式1
     * @param file
     * @param position
     * @param fileLength
     * @throws IOException
     */
    @Override
    public void breakUpload(MultipartFile file, Long position, Long fileLength) throws IOException {
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

        long currentLength = accessFile.length();
        if (currentLength >= fileLength) {
            //文件上传完毕

        }

    }

    /**
     * 断点上传 实现方式2
     * @param file
     * @param chunks
     * @param chunkNum
     */
    @Override
    public void breakUpload2(MultipartFile file, Integer chunks, Integer chunkNum) throws IOException {
        if (chunkNum > chunks) {
            return;
        }
        String storePath = "D:\\";
        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        RandomAccessFile accessFile = new RandomAccessFile(storePath + filename, "rw");
        //分块大小，与前端一致
        int chunkSize = 10 * 1024 * 1024;
        //获取断点位置
        long position = chunks * (chunkSize - 1);
        accessFile.seek(position);
        int b = 0;
        while ( -1 != (b = inputStream.read())) {
            accessFile.write(b);
        }

        accessFile.close();

        RandomAccessFile successFile = new RandomAccessFile(storePath + filename + ".conf", "rw");
        //创建conf文件文件长度为总分片数，每上传一个分块即向conf文件中写入一个127，那么没上传的位置就是默认0,已上传的就是Byte.MAX_VALUE 127
        successFile.setLength(chunks);
        successFile.seek(chunkNum);
        successFile.write(Byte.MAX_VALUE);
        successFile.close();

    }

    public static void testUpload2() throws IOException {
        RandomAccessFile successFile = new RandomAccessFile("D:\\test.conf", "rw");
        //创建conf文件文件长度为总分片数，每上传一个分块即向conf文件中写入一个127，那么没上传的位置就是默认0 ,已上传的就是Byte.MAX_VALUE 127
        successFile.setLength(12);
        successFile.seek(0);
        successFile.write(Byte.MAX_VALUE);
        successFile.close();
    }

    public static int getFailChunk() throws IOException {
        RandomAccessFile successFile = new RandomAccessFile("D:\\test.conf", "rw");
        byte[] bytes = new byte[(int) successFile.length()];
        successFile.read(bytes);
        successFile.close();
        //获取上传未完成的坐标
        for (int i = 0; i < bytes.length; i++) {
            int i1 = bytes[i] & Byte.MAX_VALUE;
            if (i1 == 0) {
                return i;
            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        testUpload2();
        System.out.println(getFailChunk());
    }
}