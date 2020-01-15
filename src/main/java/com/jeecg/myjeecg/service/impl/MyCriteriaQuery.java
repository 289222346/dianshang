package com.jeecg.myjeecg.service.impl;

import com.myjdbc.jdbc.core.service.CriteriaQuery;
import com.myjdbc.jdbc.core.service.impl.CriteriaQueryOracle;

public class MyCriteriaQuery extends CriteriaQueryOracle implements CriteriaQuery {

    public MyCriteriaQuery(Class cls) {
        super(cls);
    }

    public <T> MyCriteriaQuery(Class<T> cls, T t) {
        super(cls, t);
    }
}
