/**
 * Log.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class Log extends BaseModel implements Serializable {
    /**
     * 谁、用户id
     */
    private Long userId;

    /**
     * 什么地点ip、访问ip
     */
    private Integer remoteAddr;

    /**
     * 做了什么、调用的类名
     */
    private String requestClass;

    /**
     * 做了什么、调用的方法名
     */
    private String requestMethod;

    /**
     * 方法的参数
     */
    private String args;

    private static final long serialVersionUID = 1L;

}