/**
 * Resource.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class Resource extends BaseModel implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 类型  0：模块，1：页面，2：操作
     */
    private Byte type;

    /**
     * 资源路径
     */
    private String path;

    private static final long serialVersionUID = 1L;

}