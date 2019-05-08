/**
 * RoleResource.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
@Data
public class RoleResource extends BaseModel implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 资源id
     */
    private Long resourceId;

    private static final long serialVersionUID = 1L;

}