package com.obatis.test.dao;

import com.obatis.core.DBHandleFactory;
import com.obatis.test.model.TestModel;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO extends DBHandleFactory<TestModel> {
}
