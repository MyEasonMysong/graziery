/**
 * Org.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class Org extends BaseModel implements Serializable {
    /**
     * 机构名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 全部父Id,按照层级关系从最远父级到最近父级使用逗号分隔
     */
    private String parentsId;

    private static final long serialVersionUID = 1L;
    /** 机构所属用户 */
    private List<User> users = new ArrayList<>(16);


}