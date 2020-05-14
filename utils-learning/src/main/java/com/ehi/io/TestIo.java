package com.ehi.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TestIo
 *
 * @Author: WangYiHai
 * @Date: 2020/5/14 11:49
 * @Description: TODO
 */
public class TestIo {
    public static void main(String[] args) throws IOException {

        //-------------FileUtils-文件操作工具类----------
        // 拷贝文件
        File fileA = new File("E:\\test\\test.txt");
        File fileB = new File("E:\\test1\\test.txt");
        FileUtils.copyFile(fileA,fileB);

        // 获取指定文件夹上所有文件
        // 按照指定文件后缀如java,txt等去查找指定文件夹的文件
        File directory = new File("E:\\test");
        FileUtils.listFiles(directory, new String[]{"txt"}, false);

        // 读取指定文件所有行 不需要使用 while 循环读取流了
        List<String> lines = FileUtils.readLines(fileA,"UTF-8");
        // 可以一行行写入文本
        List<String> lines1 = new ArrayList<>();
        FileUtils.writeLines(fileA,"UTF-8",lines1);

        //-----------------IOUtils-I/O 操作相关工具类-------------------
        //FileUtils 主要针对相关文件操作，IOUtils 更加针对底层 I/O,可以快速读取 InputStream。实际上 FileUtils 底层操作依赖就是 IOUtils

        //IOUtils可以适用于一个比较试用的场景，比如支付场景下，HTTP 异步通知场景。如果我们使用 JDK 原生方法写:
        HttpServletRequest request = null;
        byte[] b = null;
        ByteArrayOutputStream baos = null;
        String respMsg = null;
        try {
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();
            // 获取输入流
            InputStream in = request.getInputStream();
            for (int len = 0; (len = in.read(buffer)) > 0; ) {
                baos.write(buffer, 0, len);
            }
            b = baos.toByteArray();
            baos.close();
            // 字节数组转化成字符串
            String reqMessage = new String(b, "utf-8");
        } catch (IOException e) {

        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {

                }
            }
        }

        //上面代码说起来还是挺复杂的。不过我们使用 IOUtils，一个方法就可以简单搞定：
        // 将输入流信息全部输出到字节数组中
        byte[] b1 = IOUtils.toByteArray(request.getInputStream());
        // 将输入流信息转化为字符串
        String resMsg = IOUtils.toString(request.getInputStream(),"utf-8");

    }
}