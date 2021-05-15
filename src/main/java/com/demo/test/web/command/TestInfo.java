package com.demo.test.web.command;

import com.obatis.config.response.result.ResultInfo;
import com.obatis.core.annotation.validator.IsPhoneNumber;
import com.obatis.core.annotation.validator.IsRange;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TestInfo extends ResultInfo {

    @ApiModelProperty(value = "类型")
    @IsRange(message = "类型只能为1,2,3,4,5", value = "1,2,3,4,5")
    private int type;
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;
    @ApiModelProperty(value = "手机号码")
    @IsPhoneNumber(message = "请输入正确的手机号码")
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
