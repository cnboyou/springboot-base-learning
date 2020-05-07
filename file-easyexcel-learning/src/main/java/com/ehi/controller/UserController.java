package com.ehi.controller;

import com.ehi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 17:11
 * @Description: 用户管理
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * @Description: excel导出
     * @author: lst
     * @date: 2020-4-28 11:00
     */
    @GetMapping(value = "/user-export", produces = "application/json; charset=utf-8")
    public void  userExport() {
        userService.userExport();
    }

    /**
     * @Description 将用户信息导出下载
     * @author lst
     * @date 2020-4-28 14:54
     * @param response
     */
    @GetMapping(value = "/download-export", produces = "application/json; charset=utf-8")
    public void downLoadExcel(HttpServletResponse response){
        userService.downLoadExcel(response);
    }

    /**
     * @Description  Excel文件用户信息导入
     * @author lst
     * @date 2020-4-28 15:18
     * @param file 文件流
     */
    @PostMapping(value = "/user-import", produces = "application/json; charset=utf-8")
    @ApiOperation(value = "Excel文件用户信息导入", notes = "Excel文件用户信息导入", produces = "application/json")
    public void  userImport(@RequestParam(value = "file") MultipartFile file) {
        userService.userImport(file);
    }

}