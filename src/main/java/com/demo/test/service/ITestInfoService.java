package com.demo.test.service;

import com.obatis.config.request.PageParam;
import com.obatis.config.response.result.PageInfo;
import com.demo.test.model.TestInfoModel;
import com.demo.test.web.command.TestInfo;
import com.obatis.exception.HandleException;

import java.util.List;

public interface ITestInfoService {

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
    List<TestInfoModel> list();

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<TestInfo> listByParams(TestInfo params);

    /**
     * 分页查询
     * @param param
     * @return
     */
    PageInfo<TestInfo> page(PageParam param);

    void delete();
}
