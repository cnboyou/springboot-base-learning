package com.ehi.service.impl;

import com.ehi.model.UserExcelVO;
import com.ehi.service.UserService;
import com.ehi.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 14:10
 * @Description: TODO
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 导出
     *
     * @param response
     */
    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<UserExcelVO> excelVOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserExcelVO userExcelVO = new UserExcelVO();
            userExcelVO.setIcCar("00"+(i+1));
            userExcelVO.setUsername("Lisi" + i);
            userExcelVO.setPhoneNumber("134"+i+i+i);
            userExcelVO.setDepartmentName("研发部");
            userExcelVO.setEmail("12249"+ i + "@qq.com");
            userExcelVO.setRoleName("经理");
            excelVOList.add(userExcelVO);
        }
        ExcelUtils.exportExcel(excelVOList,"员工信息表","员工信息", UserExcelVO.class,"员工信息", response);
    }

    /**
     * 导入
     */
    @Override
    public void importExcel() throws IOException {
        InputStream inputStream = new FileInputStream("D:\\用户模板.xlsx");
        List<UserExcelVO> userExcelVOList = ExcelUtils.importExcel(inputStream, 1, 1,false, UserExcelVO.class);

        for (UserExcelVO userExcelVO : userExcelVOList) {
            logger.info(userExcelVO.toString());
        }

    }
}