package com.ehi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * ClassName: UserExcelVO
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 10:28
 * @Description: TODO
 */
public class UserExcelVO extends BaseRowModel {

    /**
     * 工号
     */
    @ExcelProperty(value = "工号", index = 0)
    private String icCar;
    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名", index = 1)
    private String username;
    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码", index = 2)
    private String phoneNumber;
    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱", index = 3)
    private String email;
    /**
     * 角色
     */
    @ExcelProperty(value = "角色", index = 4)
    private String roleName;
    /**
     * 所属部门
     */
    @ExcelProperty(value = "所属部门", index = 5)
    private String departmentName;

    public String getIcCar() {
        return icCar;
    }

    public void setIcCar(String icCar) {
        this.icCar = icCar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}