package com.sxkj.exception;

import io.swagger.models.auth.In;

/**
 * com.sxkj.exception.ExceptionEnum
 *
 * @author zwd
 * @Description ExceptionEnum
 * @Date Create in 2018-07-17 0017 15:06
 * @Modified
 */
public enum ExceptionEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    USER_NOT_FOUND(-101,"用户不存在");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
