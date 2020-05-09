package com.ehi.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: UserService
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 14:08
 * @Description: TODO
 */
public interface UserService {

    /**
     * 导出
     * @param response
     */
    void exportExcel(HttpServletResponse response) throws IOException;

    /**
     * 导入
     */
    void importExcel() throws IOException;

}