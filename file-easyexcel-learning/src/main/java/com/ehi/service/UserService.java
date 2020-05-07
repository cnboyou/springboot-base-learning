package com.ehi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: UserService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 17:14
 * @Description: TODO
 */
public interface UserService {

    /**
     * @Description: excel导出
     * @author: lst
     * @date: 2020-4-28 11:00
     */
    void userExport();

    /**
     * @Description 将用户信息导出下载
     * @author lst
     * @date 2020-4-28 14:54
     * @param response
     */
    void downLoadExcel(HttpServletResponse response);

    /**
     * @Description  Excel文件用户信息导入
     * @author lst
     * @date 2020-4-28 15:18
     * @param file 文件流
     */
    void userImport(MultipartFile file);
}