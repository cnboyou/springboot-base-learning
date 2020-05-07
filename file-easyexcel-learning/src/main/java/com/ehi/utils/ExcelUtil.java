package com.ehi.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.StringUtils;
import com.ehi.model.UserExcelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: ExcelUtil
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 17:06
 * @Description: 阿里Excel工具类
 */
@Slf4j
public class ExcelUtil {

    private static Sheet initSheet;

    /**
     * 默认初始化
     */
    static {
        //sheetNo: sheet页码，默认为1
        //headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    /**
     * @Description: 导入方法，读取少于1000行数据
     * @author: lst
     * @date: 2020-4-28 11:26
     * @param filePath 文件绝对路径
     * @return List<Object>
     */
    public static List<Object> readLessThan1000Row(String filePath){
        return readLessThan1000RowBySheet(filePath,null);
    }

    /**
     * @Description: 导入方法，读小于1000行数据, 带样式
     * @author: lst
     * @date: 2020-4-28 11:27
     * @param filePath 文件绝对路径
     * @param sheet sheet对象
     *      sheetNo: sheet页码，默认为1
     *      headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
     *      clazz: 返回数据List<Object> 中Object的类名
     * @return List<Object>
     */
    public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet){
        if(!StringUtils.hasText(filePath)){
            return null;
        }
        //判断是否使用默认的sheet
        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            return EasyExcelFactory.read(fileStream, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(fileStream != null){
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * @Description  导入方法,读大于1000行数据
     * @author lst
     * @date 2020-4-28 11:32
     * @param filePath 文件绝对路径
     * @return List<Object>
     */
    public static List<Object> readMoreThan1000Row(String filePath){
        return readMoreThan1000RowBySheet(filePath,null);
    }

    /**
     * @Description  导入方法,读大于1000行数据, 带样式
     * @author lst
     * @date 2020-4-28 11:33
     * @param filePath 文件绝对路径
     * @param sheet sheet对象
     *      sheetNo: sheet页码，默认为1
     *      headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
     *      clazz: 返回数据List<Object> 中Object的类名
     * @return List<Object>
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet){
        if(!StringUtils.hasText(filePath)){
            return null;
        }
        //判断是否使用默认的sheet
        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListenerPlus excelListener = new ExcelListenerPlus();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            return excelListener.getDatas();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(fileStream != null){
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * @Description 导出方法,生成excle
     * @author lst
     * @date 2020-4-28 11:37
     * @param filePath  绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param head 表头
     */
    public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head){
        writeSimpleBySheet(filePath,data,head,null);
    }

    /**
     * @Description  导出方法,生成excle
     * @author lst
     * @date 2020-4-28 11:37
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param sheet excle页面样式
     * @param head 表头
     */
    public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet){
        //判断是否使用默认的sheet
        sheet = (sheet != null) ? sheet : initSheet;

        if(head != null){
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data,sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }

            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * @Description 导出方法,生成excle
     * @author lst
     * @date 2020-4-28 11:39
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data){
        writeWithTemplateAndSheet(filePath,data,null);
    }

    /**
     * @Description  导出方法,生成excle
     * @author lst
     * @date 2020-4-28 11:40
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data 数据源
     * @param sheet excle页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet){
        if(CollectionUtils.isEmpty(data)){
            return;
        }
        //判断是否使用默认的sheet
        sheet = (sheet != null) ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data,sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * @Description  导出方法,生成多Sheet的excle
     * @author lst
     * @date 2020-4-28 11:41
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param multipleSheelPropetys
     * @return
     */
    public static void writeWithMultipleSheel(String filePath,List<MultipleSheelPropety> multipleSheelPropetys){
        if(CollectionUtils.isEmpty(multipleSheelPropetys)){
            return;
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
                Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
                if(!CollectionUtils.isEmpty(multipleSheelPropety.getData())){
                    sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
                }
                writer.write(multipleSheelPropety.getData(), sheet);
            }

        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        }finally {
            try {
                if(writer != null){
                    writer.finish();
                }

                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }


    /*********************匿名内部类开始，可以提取出去******************************/

    @Data
    public static class MultipleSheelPropety{
        private List<? extends BaseRowModel> data;
        private Sheet sheet;
    }


    /**
     * 解析监听器，读取大于一千行的数据需要传入次参数
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     * @author lst
     * @date 2020-4-28 11:42
     */
    @Data
    public static class ExcelListenerPlus  extends AnalysisEventListener {

        private List<Object> datas = new ArrayList<>();

        /**
         * @Description  逐行解析
         * @author lst
         * @date 2020-4-28 11:43
         * @param object  当前行的数据
         * @param context
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            // context.getCurrentRowNum()
            if (object != null) {
                datas.add(object);
            }
        }

        /**
         * @Description 解析完所有数据后会调用该方法
         * @author lst
         * @date 2020-4-28 11:43
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
        }
    }

    public static class ExcelListener extends AnalysisEventListener<UserExcelProperty> {
        private List<UserExcelProperty> datas = new ArrayList<>();
        private static final int BATCH_COUNT = 1;

        @Override
        public void invoke(UserExcelProperty userExcelProperty, AnalysisContext analysisContext) {
            //数据存储到datas，供批量处理，或后续自己业务逻辑处理。
            datas.add(userExcelProperty);
            //达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if(datas.size() >= BATCH_COUNT){
                data();
                // 存储完成清理datas
                datas.clear();
            }
        }

        private void data() {
            for(UserExcelProperty userExcelProperty : datas){
                log.info("姓名：{},性别:{},年龄:{}",userExcelProperty.getName(),userExcelProperty.getSex(),userExcelProperty.getAge());
            }
        }

        /**
         * 所有数据解析完成了 都会来调用
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {

        }
    }
}