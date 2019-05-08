/**
 * OrgMapper.java
 * @author sxkj
 * 2018-07-18 09:10:15
 */
package com.sxkj.gaia.mapper;

import com.sxkj.base.BaseMapper;
import com.sxkj.gaia.model.Org;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrgMapper extends BaseMapper<Org> {
    /**
     * 根据id获取带有user信息的org对象
     * @param id
     * @return
     */
    Org findById(Long id);
}