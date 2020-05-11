package com.ehi.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ehi.config.listener.ExcelListener;
import com.ehi.model.UserExcelVO;
import com.ehi.service.UserExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserExcelServiceImpl
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 10:38
 * @Description: TODO
 */
@Service
public class UserExcelServiceImpl implements UserExcelService {
    private static final Logger logger = LoggerFactory.getLogger(UserExcelServiceImpl.class);
    /**
     * 生成excel模板，无表头
     *
     * @throws FileNotFoundException
     */
    @Override
    public void ExcelExport() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("D:\\excel.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1,0);
            writer.write(null,sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出用户模板
     */
    @Override
    public void UserExcelExport() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("D:\\用户模板.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1,0,UserExcelVO.class);
            writer.write(null,sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导出用户数据
     */
    @Override
    public void UserInfoExport() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("D:\\用户模板.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1,0,UserExcelVO.class);
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
            writer.write(excelVOList,sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导入
     */
    @Override
    public void UserExcelImport() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("D:\\用户模板.xlsx");
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader reader = new ExcelReader(inputStream, null, listener);
            reader.read(new Sheet(1,1,UserExcelVO.class));
            List datas = listener.getDatas();
            for (Object data : datas) {
                UserExcelVO userExcelVO = (UserExcelVO) data;
                logger.info("姓名：{}",userExcelVO.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}