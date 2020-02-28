package com.obatis.test.web;

import com.obatis.test.model.TestModel;
import com.obatis.test.service.ITestService;
import com.obatis.test.web.command.TestInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private ITestService testService;

    @PostMapping(value = "add")
    @ApiOperation(value = "添加", produces = "application/json")
    public void add(@RequestBody @Valid TestInfo info) {
        this.testService.add(info);
    }

    @GetMapping("list")
    @ApiOperation(value = "查询列表", produces = "application/json")
    public List<TestModel> list() {
        return this.testService.list();
    }

    @RequestMapping(value = "listByParams", method = RequestMethod.GET)
    @ApiOperation(value = "带条件查询", produces = "application/json")
    public List<TestInfo> listByParams(@Valid TestInfo params) {
        return this.testService.listByParams(params);
    }
}
