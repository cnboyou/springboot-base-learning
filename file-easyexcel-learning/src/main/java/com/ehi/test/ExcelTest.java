package com.ehi.test;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.ehi.config.listener.ExcelListener;
import com.ehi.model.UserExcelVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassName: ExcelTest
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 10:41
 * @Description: TODO
 */
public class ExcelTest {
    private static final Logger logger = LoggerFactory.getLogger(ExcelTest.class);
    /**
     * 数据导入
     * @param file
     */
    public List importExcel(MultipartFile file) {
        InputStream inputStream = null;
        List datas = null;
        //InputStream inputStream = new FileInputStream("D:\\用户模板.xlsx");
        try {
            inputStream = file.getInputStream();
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader reader = new ExcelReader(inputStream, null, listener);
            reader.read(new Sheet(1,1,UserExcelVO.class));
            datas = listener.getDatas();
            for (Object data : datas) {
                UserExcelVO userExcelVO = (UserExcelVO) data;
                logger.info("姓名：{}",userExcelVO.getUsername());
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}