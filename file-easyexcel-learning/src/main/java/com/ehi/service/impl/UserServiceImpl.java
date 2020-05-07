package com.ehi.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ehi.model.UserExcelProperty;
import com.ehi.service.UserService;
import com.ehi.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 17:13
 * @Description: TODO
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * @Description: excel导出
     * @author: lst
     * @date: 2020-4-28 11:00
     */
    @Override
    public void userExport() {
        Long  startTime  = System.currentTimeMillis();
        //sheetNo: sheet页码，默认为1
        //headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
        Sheet sheet = new Sheet(1,0, UserExcelProperty.class,"用户信息",null);
        sheet.setAutoWidth(Boolean.TRUE);
        //设置列宽 设置每列的宽度
		/*Map columnWidth = new HashMap();
		columnWidth.put(0,10000);
        columnWidth.put(1,40000);columnWidth.put(2,10000);columnWidth.put(3,10000);
		sheet1.setColumnWidthMap(columnWidth);
		sheet1.setHead(createTestListStringHead2());*/

        List<UserExcelProperty> data = new ArrayList<>();
        for(int i = 0; i < 2000; i++){
            UserExcelProperty userExcelProperty = new UserExcelProperty();
            userExcelProperty.setAge(i);
            userExcelProperty.setName("测试"+i);
            userExcelProperty.setSex("1");
            data.add(userExcelProperty);
        }
        ExcelUtil.writeWithTemplateAndSheet("D:\\easyexcelTest.xlsx",data,sheet);

        Long  endTime  = System.currentTimeMillis();
        log.info("消耗时间：{}ms",endTime-startTime);

    }

    /**
     * @Description 将用户信息导出下载
     * @author lst
     * @date 2020-4-28 14:54
     * @param response
     */
    @Override
    public void downLoadExcel(HttpServletResponse response) {
        Long  startTime  = System.currentTimeMillis();
        ExcelWriter writer = null;
        // 文件输出位置
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(response.getOutputStream(), ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1,0, UserExcelProperty.class,"用户信息",null);
            sheet.setAutoWidth(Boolean.TRUE);
            //得到要填充的数据
            List<UserExcelProperty> data = new ArrayList();
            for(int i = 0; i < 2000; i++){
                UserExcelProperty userExcelProperty = new UserExcelProperty();
                userExcelProperty.setAge(i);
                userExcelProperty.setName("测试"+i);
                userExcelProperty.setSex("1");
                data.add(userExcelProperty);
            }
            writer.write(data, sheet);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("用户信息.xls").getBytes(), "ISO8859-1"));
            out.flush();
        } catch (IOException e) {
            log.info("错误信息：{}",e.getMessage());
        }finally {
            writer.finish();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Long  endTime  = System.currentTimeMillis();
        log.info("消耗时间：{}ms",endTime-startTime);

    }

    /**
     * @Description  Excel文件用户信息导入
     * @author lst
     * @date 2020-4-28 15:18
     * @param file 文件流
     */
    @Override
    public void userImport(MultipartFile file) {
        Long  startTime  = System.currentTimeMillis();
        if(!file.isEmpty()){
            try {
                InputStream inputStream = new BufferedInputStream(file.getInputStream());
                //实例化实现了AnalysisEventListener接口的类
                ExcelUtil.ExcelListener excelListener = new ExcelUtil.ExcelListener();
                ExcelReader reader = new ExcelReader(inputStream,null,excelListener);
                //读取信息
                reader.read(new Sheet(1,1,UserExcelProperty.class));
            } catch (IOException e) {
                log.info("错误信息：{}",e.getMessage());
            }
        }
        Long  endTime  = System.currentTimeMillis();
        log.info("消耗时间：{}ms",endTime-startTime);
    }
}