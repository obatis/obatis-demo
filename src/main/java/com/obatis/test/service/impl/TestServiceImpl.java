package com.obatis.test.service.impl;

import com.obatis.convert.BeanCommonConvert;
import com.obatis.core.exception.HandleException;
import com.obatis.core.sql.QueryProvider;
import com.obatis.test.dao.TestDAO;
import com.obatis.test.model.TestModel;
import com.obatis.test.model.fields.TestField;
import com.obatis.test.service.ITestService;
import com.obatis.test.web.command.TestInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {

    @Resource
    private TestDAO testDAO;

    /**
     * 添加
     * @param info
     * @throws HandleException
     */
    @Override
    public void add(TestInfo info) throws HandleException {
        TestModel model = BeanCommonConvert.convert(info, TestModel.class);
        this.testDAO.insert(model);
    }

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<TestModel> list() {
        /**
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = new QueryProvider();
        return this.testDAO.list(provider);
    }

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    @Override
    public List<TestInfo> listByParams(TestInfo params) {
        QueryProvider provider = new QueryProvider();
        provider.equals(TestField.FIELD_USER_NAME, params.getUserName());
        provider.equals(TestField.FIELD_TYPE, params.getType());
        return this.testDAO.list(provider, TestInfo.class);
    }
}
