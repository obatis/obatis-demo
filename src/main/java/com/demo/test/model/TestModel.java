package com.demo.test.model;

import com.obatis.core.CommonModel;
import com.obatis.core.annotation.Column;
import com.obatis.core.annotation.Table;
import com.demo.test.model.fields.TestField;

import java.time.LocalDate;

@Table(name = TestField.TABLE_NAME)
public class TestModel extends CommonModel {

    // 如果数据库字段和属性值一样，可以不用加 @Column 注解

    /**
     * 类型
     */
    private int type;
    /**
     * 用户名
     */
    @Column(name = TestField.FIELD_USER_NAME)
    private String userName;
    /**
     * 生日
     */
    private LocalDate birthday;

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
}
