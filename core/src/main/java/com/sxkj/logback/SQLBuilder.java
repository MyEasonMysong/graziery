package com.sxkj.logback;

import com.sxkj.logback.namse.ColumnNames;
import com.sxkj.logback.namse.TableAndColumnNameResolver;

/**
 * com.sxkj.logback.SQLBuilder
 *
 * @author zwd
 * @Description SQLBuilder
 * @Date Create in 2018-07-24 0024 10:58
 * @Modified
 */
public class SQLBuilder {
    private SQLBuilder() { super(); }

    public static String buildInsertPropertiesSQL(TableAndColumnNameResolver resolver) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(resolver.getLoggingEventPropertyTableName()).append(" (");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.EVENT_ID)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.MAPPED_KEY)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.MAPPED_VALUE)).append(") ");
        sqlBuilder.append("VALUES (?, ?, ?)");
        return sqlBuilder.toString();
    }

    public static String buildInsertExceptionSQL(TableAndColumnNameResolver resolver) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(resolver.getLoggingEventExceptionTableName()).append(" (");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.EVENT_ID)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.I)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.TRACE_LINE)).append(") ");
        sqlBuilder.append("VALUES (?, ?, ?)");
        return sqlBuilder.toString();
    }

    public static String buildInsertSQL(TableAndColumnNameResolver resolver) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(resolver.getLoggingEventTableName()).append(" (");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.TIMESTMP)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.FORMATTED_MESSAGE)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.LOGGER_NAME)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.LEVEL_STRING)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.THREAD_NAME)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.REFERENCE_FLAG)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG0)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG1)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG2)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG3)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG4)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG5)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG6)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG7)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG8)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG9)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG10)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG11)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG12)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG13)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG14)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG15)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG16)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG17)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG18)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG19)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG20)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG21)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG22)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG23)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG24)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG25)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG26)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG27)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG28)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG29)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG30)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.ARG31)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.CALLER_FILENAME)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.CALLER_CLASS)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.CALLER_METHOD)).append(", ");
        sqlBuilder.append(resolver.getColumnName(ColumnNames.CALLER_LINE)).append(") ");
        sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return sqlBuilder.toString();
    }
}
