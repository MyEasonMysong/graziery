/**
 * User.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.model;

import com.sxkj.base.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class User extends BaseModel implements Serializable {
    /**
     * 名称
     */
    private String name;

    /**
     * 机构id
     */
    private Long orgId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    private static final long serialVersionUID = 1L;

    private Org orgInfo;

    private List<Role> roles = new ArrayList<>(16);

    private List<Resource> resources = new ArrayList<>(16);

    public Set<String> permissions(List<Resource> resources){
        Set<String> permissions = new HashSet<>(16);
        if(null!=resources&&!resources.isEmpty()){
            for (Resource resource : resources) {
                permissions.add(resource.getPath());
            }
            return permissions;
        }
        return null;
    }

}