package com.demo.test.web;

import com.demo.test.web.output.TestOutput;
import com.sbatis.convert.JsonCommonConvert;
import com.sbatis.core.exception.HandleException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public void add() {
        System.out.println("success!!!");
    }

    @RequestMapping(value = "find", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public TestOutput find() {
        TestOutput output = new TestOutput();
        output.setType(1);
        output.setName("小明");
        output.setDate(new Date());


//        throw new HandleException("test error");

        return output;
    }

    public static void main(String[] args) {
        TestOutput output = new TestOutput();
        output.setType(1);
        output.setName("小明");
        output.setDate(new Date());
        System.out.println(JsonCommonConvert.objConvertJson(output));
    }

    @PostMapping
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<TestOutput> list() {
        List<TestOutput> list = new ArrayList<>();
        TestOutput output = new TestOutput();
        output.setType(1);
        output.setName("小明");
        output.setDate(new Date());
        list.add(output);
        return list;
    }

    @RequestMapping(value = "listByParams", method = RequestMethod.GET)
    public List<TestOutput> listByParams(TestOutput params) {
        System.out.println(JsonCommonConvert.objConvertJson(params));
        return this.list();
    }
}
