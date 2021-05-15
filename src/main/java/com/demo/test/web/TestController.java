package com.demo.test.web;

import com.demo.test.model.TestInfoModel;
import com.demo.test.service.ITestInfoService;
import com.demo.test.web.command.TestInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "【测试模块】-展示接口访问和注解配置")
@RestController
@RequestMapping("testInfo")
public class TestController {

    @Resource
    private ITestInfoService testInfoService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加", produces = "application/json")
    public void add(@RequestBody @Valid TestInfo info) {
        this.testInfoService.add(info);
    }

    @GetMapping("list")
    @ApiOperation(value = "查询列表", produces = "application/json")
    public List<TestInfoModel> list() {
        return this.testInfoService.list();
    }

    @RequestMapping(value = "listByParams", method = RequestMethod.GET)
    @ApiOperation(value = "带条件查询", produces = "application/json")
    public List<TestInfo> listByParams(@Valid TestInfo params) {
        return this.testInfoService.listByParams(params);
    }
}
