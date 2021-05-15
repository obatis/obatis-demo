package com.demo.test.model;

import com.demo.test.model.fields.TestInfoField;
import com.obatis.orm.annotation.Column;
import com.obatis.orm.annotation.Table;
import com.obatis.orm.model.type.CommonBigIntegerModel;

import java.time.LocalDate;

@Table(name = TestInfoField.TABLE_NAME)
public class TestInfoModel extends CommonBigIntegerModel {

    // 如果数据库字段和属性值一样，可以不用加 @Column 注解

    /**
     * 类型
     */
    private int type;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 用户名
     */
    @Column(name = TestInfoField.FIELD_USER_NAME)
    private String userName;
    /**
     * 手机号码
     */
    @Column(name = TestInfoField.FIELD_PHONE_NUMBER)
    private String phoneNumber;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
