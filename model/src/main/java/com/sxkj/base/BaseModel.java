package com.sxkj.base;

import lombok.Data;

/**
 * com.sxkj.base.BaseModel
 * model基类，存储公共属性
 * @author zwd
 * @Description BaseModel
 * @Date Create in 2018-05-17 0017 11:09
 * @Modified
 */
@Data
public class BaseModel {
    /** 主键id */
    private Long id;
    /** 数据创建用户id，系统字段，禁止挪作业务用途 */
    private Long createId;
    /** 数据修改用户id，系统字段，禁止挪作业务用途 */
    private Long updateId;
    /** 数据状态，1：可用，0：不可用，系统字段，禁止挪作业务用途 */
    private Byte statusFlag;
    /** 备注，系统字段，禁止挪作业务用途 */
    private String remark;
    /** 排序，系统字段，禁止挪作业务用途 */
    private Integer sort;

    /**
     * 新增初始化
     * @param createId 创建者id
     */
    public void insertInit(Long createId){
        this.createId = createId==null?0L:createId;
        this.updateId = this.createId;
        this.statusFlag = 1;
        this.sort = 0;
    }

    /**
     * 修改初始化
     * @param updateId 修改者id
     */
    public void updateInit(Long updateId){
        this.updateId = updateId==null?0L:updateId;
    }

}
