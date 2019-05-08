package com.sxkj.logback.namse;

/**
 * com.sxkj.logback.namse.TableNameResolver
 *
 * @author zwd
 * @Description TableNameResolver
 * @Date Create in 2018-07-24 0024 10:57
 * @Modified
 */
public interface TableNameResolver {
    String getLoggingEventTableName();

    String getLoggingEventExceptionTableName();

    String getLoggingEventPropertyTableName();
}
