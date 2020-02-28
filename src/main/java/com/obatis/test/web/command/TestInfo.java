package com.obatis.test.web.command;

import com.obatis.core.result.ResultInfoOutput;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TestInfo extends ResultInfoOutput {

    @ApiModelProperty(value = "类型")
    private int type;
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty(value = "出生日期")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
