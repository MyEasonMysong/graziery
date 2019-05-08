package com.sxkj.logback.namse;

/**
 * com.sxkj.logback.namse.DefaultTableAndColumnNameResolver
 *
 * @author zwd
 * @Description DefaultTableAndColumnNameResolver
 * @Date Create in 2018-07-24 0024 10:55
 * @Modified
 */
public class DefaultTableAndColumnNameResolver implements TableAndColumnNameResolver {

    private String loggingEventTableName = "logging_event";
    private String loggingEventExceptionTableName = "logging_event_exception";
    private String loggingEventPropertyTableName = "logging_event_property";

    public String getLoggingEventTableName() {
        return this.loggingEventTableName;
    }

    public String getLoggingEventExceptionTableName() {
        return this.loggingEventExceptionTableName;
    }

    public String getLoggingEventPropertyTableName() {
        return this.loggingEventPropertyTableName;
    }

    public String getColumnName(Object object) {
        return object.toString().toLowerCase();
    }

    // setter
    // -------------------------------------------------------------------------------------------------------------------

    public void setLoggingEventTableName(String loggingEventTableName) {
        this.loggingEventTableName = loggingEventTableName;
    }

    public void setLoggingEventExceptionTableName(String loggingEventExceptionTableName) {
        this.loggingEventExceptionTableName = loggingEventExceptionTableName;
    }

    public void setLoggingEventPropertyTableName(String loggingEventPropertyTableName) {
        this.loggingEventPropertyTableName = loggingEventPropertyTableName;
    }

}
