/**
 * RoleOrg.java
 * @author sxkj
 * 2018-07-23 14:37:09
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class RoleOrg extends BaseModel implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 机构id
     */
    private Long orgId;

    private static final long serialVersionUID = 1L;


}