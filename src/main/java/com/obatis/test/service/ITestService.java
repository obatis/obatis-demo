package com.obatis.test.service;

import com.obatis.core.exception.HandleException;
import com.obatis.test.model.TestModel;
import com.obatis.test.web.command.TestInfo;

import java.util.List;

public interface ITestService {

    /**
     * 添加
     * @param info
     * @throws HandleException
     */
    void add(TestInfo info) throws HandleException;

    /**
     * 查询列表
     * @return
     */
    List<TestModel> list();

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<TestInfo> listByParams(TestInfo params);
}
