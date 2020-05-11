package com.ehi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * ClassName: UserExcelVO
 *
 * @Author: WangYiHai
 * @Date: 2020/5/9 10:28
 * @Description: TODO
 */
public class UserExcelVO {

    /**
     * 工号
     */
    @Excel(name = "工号", orderNum = "1")
    private String icCar;
    /**
     * 姓名
     */
    @Excel(name = "姓名", orderNum = "2")
    private String username;
    /**
     * 手机号码
     */
    @Excel(name = "手机号码", orderNum = "3")
    private String phoneNumber;
    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "4")
    private String email;
    /**
     * 角色
     */
    @Excel(name = "角色", orderNum = "5")
    private String roleName;
    /**
     * 所属部门
     */
    @Excel(name = "所属部门", orderNum = "6")
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

    @Override
    public String toString() {
        return "UserExcelVO{" +
                "icCar='" + icCar + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}