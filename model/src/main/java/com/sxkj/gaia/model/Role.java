/**
 * Role.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class Role extends BaseModel implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 数据范围 0：全部，1：本单位及下级，2：本单位，3：本部门及下级，4：本部门，5：本人，6：明细
     */
    private Byte range;

    private static final long serialVersionUID = 1L;

}