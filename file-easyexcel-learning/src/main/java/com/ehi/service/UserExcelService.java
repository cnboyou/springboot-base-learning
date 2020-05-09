package com.ehi.service;

import java.io.FileNotFoundException;

/**
 * ClassName: UserExcelService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 10:37
 * @Description: TODO
 */
public interface UserExcelService {

    /**
     * 生成excel模板，无表头
     * @throws FileNotFoundException
     */
    void ExcelExport() throws FileNotFoundException;

    /**
     * 导出用户数据
     */
    void UserInfoExport() throws FileNotFoundException;

    /**
     * 导出用户模板
     */
    void UserExcelExport() throws FileNotFoundException;

    /**
     * 导入
     */
    void UserExcelImport() throws FileNotFoundException;

}