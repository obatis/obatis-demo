package com.demo.test.service.impl;

import com.demo.test.model.TestInfoModel;
import com.demo.test.model.fields.TestInfoField;
import com.demo.test.service.ITestInfoService;
import com.demo.test.web.command.TestInfo;
import com.obatis.config.request.PageParam;
import com.obatis.config.response.result.PageInfo;
import com.obatis.convert.BeanConvert;
import com.obatis.exception.HandleException;
import com.obatis.orm.provider.DeleteProvider;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.UpdateProvider;
import com.obatis.orm.provider.handle.ProviderBuilder;
import com.obatis.orm.sql.SqlHandleFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TestInfoServiceImpl extends SqlHandleFactory<TestInfoModel> implements ITestInfoService {

    /**
     * 添加
     * @param info
     * @throws HandleException
     */
    @Override
    public void add(TestInfo info) throws HandleException {
        TestInfoModel model = BeanConvert.convert(info, TestInfoModel.class);
        this.insert(model);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<TestInfoModel> list() {
        /**
         * 映射sql > select * from demo;
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = ProviderBuilder.query();
        return this.list(provider);
    }

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    @Override
    public List<TestInfo> listByParams(TestInfo params) {
        /**
         * 映射sql > select * from demo where user_name = ? and type = ?;
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = ProviderBuilder.query();
        provider.equal(TestInfoField.FIELD_USER_NAME, params.getUserName());
        provider.equal(TestInfoField.FIELD_TYPE, params.getType());
        return this.list(provider, TestInfo.class);
    }

    /**
     * 分页查询
     * @param param
     * @return
     */
    @Override
    public PageInfo<TestInfo> page(PageParam param) {
        /**
         * 映射sql > select type, user_name, birthday from demo where user_name = ? and type = ? limit ?,?
         * QueryProvider 更多用法，请参考注释
         */
        QueryProvider provider = ProviderBuilder.query();
        provider.select(TestInfoField.FIELD_TYPE, TestInfoField.FIELD_USER_NAME, TestInfoField.FIELD_BIRTHDAY);
        provider.setPageInfo(param.getPage(), param.getRows());
        provider.equal(TestInfoField.FIELD_USER_NAME, "name");
        provider.equal(TestInfoField.FIELD_TYPE, 1);
        return this.page(provider, TestInfo.class);
    }

    /**
     * 更新操作
     */
    public void update() {
        /**
         * 映射sql > update demo set user_name = ? where id = ?
         * QueryProvider 更多用法，请参考注释
         */
        UpdateProvider provider = ProviderBuilder.update();
        provider.set(TestInfoField.FIELD_USER_NAME, "xiaoming");
        provider.equal(TestInfoField.FIELD_ID, "123456789");
        this.update(provider);
    }

    /**
     * 删除
     * 展示根据 QueryProvider 封装类删除和根据ID删除两种方式
     */
    public void delete() {
        /**
         * 映射sql > delete demo where user_name = ?
         * QueryProvider 更多用法，请参考注释
         */
        DeleteProvider provider = ProviderBuilder.delete();
        provider.equal(TestInfoField.FIELD_USER_NAME, "xiaoming");
        this.delete(provider);

        /**
         * 提醒：删除还提供了根据ID进行删除的方法
         * 映射sql > delete demo where id = ?
         */
        this.deleteById(new BigInteger("123456789"));
    }

    /**
     * 连接查询和 or 条件查询
     */
    public void leftJoin() {

        /**
         * 映射sql > select t.user_name, t.type, c.salary, c.company_name from demo t left join user_company_list c on t.id=c.user_id where t.type in (?,?,?) or t.user_name=?
         * 提醒：left join 如果被连接查询的表不出现 select()则默认不查询，而主表如果出现 select()则查询列举的字段，不出现则表示查询主表所有字段。如果不查询主表字段，则设置 selectNothing() 为true，详见注释说明。
         * QueryProvider 更多用法，请参考注释
         */

        QueryProvider provider = ProviderBuilder.query();
        provider.select(TestInfoField.FIELD_USER_NAME, TestInfoField.FIELD_TYPE);
        Integer[] typeArray = {1,2,3};
        provider.in(TestInfoField.FIELD_TYPE, typeArray);
        provider.orEqual(TestInfoField.FIELD_USER_NAME, "xiaoming");

        QueryProvider workProvider = ProviderBuilder.query("user_company_list");
        workProvider.select("salary", "company_name");
        provider.addleftJoin(TestInfoField.FIELD_ID, "user_id", workProvider);
        this.list(provider, TestInfo.class);

        /**
         * 拓展说明：
         * 如果要设置类似 where a = ? and (b = ? or c = ?)，则把括号内语句看成是一个QueryProvider，用 addProvider() 即可，而此QueryProvider不用设置表名，相见注释说明。
         * 如果连接查询要实现两个不同的 QueryProvider 字段运算，获取字段的方法为 getColumn()。
         * 如果要用到字段运算，建议使用 com.obatis.core.sql.QueryProviderExpHandle 表达式运算类。
         * QueryProvider 更多用法，请参考注释
         */
    }
}
