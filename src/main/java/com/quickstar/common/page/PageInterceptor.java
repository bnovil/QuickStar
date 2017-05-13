package com.quickstar.common.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author yuton
 * @version 1.0
 * @description
 * @since 2017/4/27 10:57
 */
@Slf4j
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class PageInterceptor implements Interceptor {
    private String databaseType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object obj = boundSql.getParameterObject();
        if (obj instanceof Page) {
            Page page = (Page) obj;
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            String sql = boundSql.getSql();
            setTotalRecord(page, mappedStatement, connection);
            String pageSql = getPageSql(page, sql);
            ReflectUtil.setFieldValue(boundSql, pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.databaseType = properties.getProperty("databaseType");
    }

    private String getPageSql(Page<?> page, String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql);
//        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMysqlPageSql(page, sqlBuilder);
//        } else if ("oracle".equalsIgnoreCase(databaseType)) {
//            return getOraclePageSql(page, sqlBuilder);
//        }
//        return sqlBuilder.toString();
    }

    private String getMysqlPageSql(Page page, StringBuilder sqlBuilder) {
//        sqlBuilder = new StringBuilder(getParamSql(page, sqlBuilder));
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        sqlBuilder.append(" limit ").append(offset).append(",").append(page.getPageSize());
        return sqlBuilder.toString();
    }

    private String getOraclePageSql(Page page, StringBuilder sqlBuilder) {
        int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
        sqlBuilder.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
        sqlBuilder.insert(0, "select * from (").append(") where r >= ").append(offset);
        return sqlBuilder.toString();
    }

    private void setTotalRecord(Page page, MappedStatement mappedStatement, Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        String sql = boundSql.getSql();
        String countSql = getCountSql(page, sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        PreparedStatement psst = null;
        ResultSet rs = null;
        try {
            psst = connection.prepareStatement(countSql);
            parameterHandler.setParameters(psst);
            rs = psst.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                page.setTotalRecord(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                psst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getCountSql(Page<?> page, String sql) {
        int index = sql.indexOf("from");
        String countSql = "select count(1) " + sql.substring(index);
//        countSql = getParamSql(page, new StringBuilder(countSql));
        return countSql;
    }

//    private String getParamSql(Page page, StringBuilder sql) {
//        Object bean = page.getParams();
//        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//            sql.append(" where 1=1 ");
//            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
//                String key = propertyDescriptor.getName();
//                if (!key.equals("class")) {
//                    Method getter = propertyDescriptor.getReadMethod();
//                    Object value = getter.invoke(bean);
//                    sql.append(" and ").append(key).append(" = ").append(value);
//                }
//            }
//        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
//            log.error(e.getMessage(), e);
//        }
//        return sql.toString();
//    }

    private static class ReflectUtil {

        static Object getFieldValue(Object object, String fieldName) {
            Object result = null;
            Field field = ReflectUtil.getField(object, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(object);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
            }
            return result;
        }

        static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException ignored) {
                }
            }
            return field;
        }

        static void setFieldValue(Object object, String fieldValue) {
            Field field = ReflectUtil.getField(object, "sql");
            if (field != null) {
                field.setAccessible(true);
                try {
                    field.set(object, fieldValue);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
