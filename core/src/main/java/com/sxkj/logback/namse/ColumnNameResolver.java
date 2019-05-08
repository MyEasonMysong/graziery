package com.sxkj.logback.namse;

/**
 * com.sxkj.logback.namse.ColumnNameResolver
 *
 * @author zwd
 * @Description ColumnNameResolver
 * @Date Create in 2018-07-24 0024 10:54
 * @Modified
 */
public interface ColumnNameResolver {
    String getColumnName(Object object);
}
