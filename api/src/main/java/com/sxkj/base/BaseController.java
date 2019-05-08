package com.sxkj.base;

import com.google.gson.Gson;
import com.sxkj.exception.ExceptionHandle;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * com.sxkj.base.BaseController
 *
 * @author zwd
 * @Description BaseController
 * @Date Create in 2018-05-25 0025 15:01
 * @Modified
 */
public abstract class BaseController<T extends BaseModel>  {
    @Autowired
    protected ExceptionHandle exceptionHandle;

    /**
     * 新增数据
     * @param t
     * @return
     */
    @ApiOperation(value = "新增数据",notes = "向数据库中插入新数据，返回id")
    @ApiImplicitParam(required = true,dataType = "BaseModel")
    @PostMapping(value = "")
    public abstract Result insert(@RequestBody T t);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @ApiOperation(value = "删除数据",notes = "根据id删除数据")
    @ApiImplicitParam(name = "id",value = "业务id",required = true,dataType = "Long",paramType = "path")
    @DeleteMapping(value = "/{id}")
    public abstract Result delete(@PathVariable Long id);

    /**
     * 根据id修改数据
     * @param t
     * @return
     */
    @ApiOperation(value = "修改数据",notes = "根据id修改数据")
    @ApiImplicitParam(required = true,dataType = "BaseModel")
    @PutMapping(value = "")
    public abstract Result update(@RequestBody T t);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @ApiOperation(value = "查找数据",notes = "根据id查找数据")
    @ApiImplicitParam(name = "id",value = "业务id",required = true,dataType = "Long",paramType = "path")
    @GetMapping(value = "/{id}")
    public abstract Result select(@PathVariable Long id);

//    @ApiOperation(value = "获取全部数据")
//    @GetMapping(value = "")
//    public abstract Result selectAll();

    /**
     * 根据条件查找（单表）
     * @param t 对象实例
     * @param pageNumber 当前分页页号
     * @param pageSize  分页每页记录数
     * @return
     */
    @ApiOperation(value = "根据条件查询")
    @PostMapping(value = "/{pageNumber}/{pageSize}",produces = "application/json;charset=UTF-8")
    public abstract Result findList(@RequestBody T t,@PathVariable Integer pageNumber,@PathVariable Integer pageSize);
}
