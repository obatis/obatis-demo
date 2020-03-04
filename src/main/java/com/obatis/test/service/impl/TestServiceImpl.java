package com.obatis.test.service.impl;

import com.obatis.config.request.PageParam;
import com.obatis.config.response.result.PageInfo;
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
import java.math.BigInteger;
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
     * 查询所有数据
     * @return
     */
    @Override
    public List<TestModel> list() {
        /**
         * 映射sql > select * from test;
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
        /**
         * 映射sql > select * from test where user_name = ? and type = ?;
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = new QueryProvider();
        provider.equals(TestField.FIELD_USER_NAME, params.getUserName());
        provider.equals(TestField.FIELD_TYPE, params.getType());
        return this.testDAO.list(provider, TestInfo.class);
    }

    /**
     * 分页查询
     * @param param
     * @return
     */
    @Override
    public PageInfo<TestInfo> page(PageParam param) {
        /**
         * 映射sql > select type, user_name, birthday from test where user_name = ? and type = ? limit ?,?
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = new QueryProvider();
        provider.select(TestField.FIELD_TYPE, TestField.FIELD_USER_NAME, TestField.FIELD_BIRTHDAY);
        provider.setPage(param);
        provider.equals(TestField.FIELD_USER_NAME, "name");
        provider.equals(TestField.FIELD_TYPE, 1);
        return this.testDAO.page(provider, TestInfo.class);
    }

    /**
     * 更新操作
     */
    public void update() {
        /**
         * 映射sql > update test set user_name = ? where id = ?
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = new QueryProvider();
        provider.set(TestField.FIELD_USER_NAME, "xiaoming");
        provider.equals(TestField.FIELD_ID, "123456789");
        this.testDAO.update(provider);
    }

    /**
     * 删除
     * 展示根据 QueryProvider 封装类删除和根据ID删除两种方式
     */
    public void delete() {
        /**
         * 映射sql > delete test where user_name = ?
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = new QueryProvider();
        provider.equals(TestField.FIELD_USER_NAME, "xiaoming");
        this.testDAO.update(provider);

        /**
         * 提醒：删除还提供了根据ID进行删除的方法
         * 映射sql > delete test where id = ?
         */
        this.testDAO.deleteById(new BigInteger("123456789"));
    }

    /**
     * 连接查询和 or 条件查询
     */
    public void leftJoin() {

        /**
         * 映射sql > select t.user_name, t.type, c.salary, c.company_name from test t left join user_company_list c on t.id=c.user_id where t.type in (?,?,?) or t.user_name=?
         * 提醒：left join 如果被连接查询的表不出现 select()则默认不查询，而主表如果出现 select()则查询列举的字段，不出现则表示查询主表所有字段。如果不查询主表字段，则设置 selectNothing() 为true，详见注释说明。
         * QueryProvider 更多用法，请参考注释
         */

        QueryProvider provider = new QueryProvider();
        provider.select(TestField.FIELD_USER_NAME, TestField.FIELD_TYPE);
        Integer[] typeArray = {1,2,3};
        provider.in(TestField.FIELD_TYPE, typeArray);
        provider.orEquals(TestField.FIELD_USER_NAME, "xiaoming");

        QueryProvider workProvider = new QueryProvider();
        workProvider.setJoinTableName("user_company_list");
        workProvider.select("salary", "company_name");
        provider.setleftJoin(TestField.FIELD_ID, "user_id", workProvider);
        this.testDAO.list(provider, TestInfo.class);

        /**
         * 拓展说明：
         * 如果要设置类似 where a = ? and (b = ? or c = ?)，则把括号内语句看成是一个QueryProvider，用 addProvider() 即可，而此QueryProvider不用设置表名，相见注释说明。
         * 如果连接查询要实现两个不同的 QueryProvider 字段运算，获取字段的方法为 getColumn()。
         * 如果要用到字段运算，建议使用 com.obatis.core.sql.QueryProviderExpHandle 表达式运算类。
         * QueryProvider 更多用法，请参考注释
         */
    }
}
