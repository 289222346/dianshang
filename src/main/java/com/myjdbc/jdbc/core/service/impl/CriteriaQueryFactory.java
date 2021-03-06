package com.myjdbc.jdbc.core.service.impl;

import com.myjdbc.jdbc.constants.DBType;
import com.myjdbc.jdbc.core.service.CriteriaQuery;
import com.myjdbc.jdbc.pool.DBUtil;
import com.myjdbc.jdbc.pool.DBconfig;

/**
 * 查询条件构造器
 *
 * @author 陈文
 * @Description
 * @date 2019/7/15 9:47
 */
public class CriteriaQueryFactory<T> {

    private CriteriaQueryFactory() {

    }

    public static CriteriaQuery creatCriteriaQuery(Class cls) {
        if (DBconfig.DBTYPE == DBType.ORACLE) {
            return new CriteriaQueryOracle(cls);
        }
        if (DBconfig.DBTYPE == DBType.MYSQL) {
            return new CriteriaQueryMysql(cls);
        }
        return null;
    }

    public static <T> CriteriaQuery creatCriteriaQuery(Class<T> cls, T t) {
        if (DBconfig.DBTYPE == DBType.ORACLE) {
            return new CriteriaQueryOracle(cls, t);
        }
        if (DBconfig.DBTYPE == DBType.MYSQL) {
            return new CriteriaQueryMysql(cls, t);
        }
        return null;
    }


}
