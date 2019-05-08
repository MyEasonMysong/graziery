package com.sxkj.util;

import net.bytebuddy.asm.Advice;

/**
 * com.sxkj.util.Result
 *
 * @author zwd
 * @Description Result
 * @Date Create in 2018-07-17 0017 14:58
 * @Modified
 */
public class Result<T> {
    /**
     *  error_code 状态值 1：表示成功，其他数值均为失败
     */
    private Integer status;
    /**
     *  error_msg 错误信息，若status=1，则msg=success
     */
    private String msg;
    /**
     *  返回头报文出参
     */
    private T data;
    @Override
    public String toString(){
        return "Result{status="+status+",msg="+msg+",data="+data+"}";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
