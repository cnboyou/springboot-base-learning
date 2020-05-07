package com.ehi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/7 17:08
 * @Description: UserExcelProperty实体类
 */
@Data
public class UserExcelProperty extends BaseRowModel {

    @ExcelProperty(value = "姓名",index = 0)
    private String name;

    @ExcelProperty(value = "性别",index = 1)
    private String sex;

    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
}