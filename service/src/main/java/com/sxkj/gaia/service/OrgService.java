package com.sxkj.gaia.service;

import com.sxkj.base.BaseService;
import com.sxkj.gaia.mapper.OrgMapper;
import com.sxkj.gaia.model.Org;
import com.sxkj.gaia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * com.sxkj.gaia.service.OrgService
 *
 * @author zwd
 * @Description OrgService
 * @Date Create in 2018-07-13 0013 16:56
 * @Modified
 */
@Service
public class OrgService extends BaseService<Org> {
    @Autowired
    private OrgMapper orgMapper;

    /**
     * 获取带有user信息的org对象
     * @param id org.id
     * @return
     */
    public Org findById(Long id) {
        return orgMapper.findById(id);
    }
}
