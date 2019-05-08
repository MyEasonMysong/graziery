package com.sxkj.gaia.api;

import com.sxkj.base.BaseController;
import com.sxkj.gaia.model.Resource;
import com.sxkj.gaia.service.ResourceService;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * com.sxkj.gaia.api.ResourceController
 *
 * @author zwd
 * @Description ResourceController
 * @Date Create in 2018-07-23 0023 16:23
 * @Modified
 */
@Api(value = "资源管理",description = "资源管理")
@RestController
@RequestMapping("/api/resource")
public class ResourceController extends BaseController<Resource> {
    @Autowired
    private ResourceService resourceService;

    @Override
    public Result insert(@RequestBody Resource resource) {
        try {
            return ResultUtil.success(resourceService.insert(resource));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result delete(@PathVariable Long id) {
        try {
            return ResultUtil.success(resourceService.deleteByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result update(@RequestBody Resource resource) {
        try {
            return ResultUtil.success(resourceService.updateByPrimaryKey(resource));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result select(@PathVariable Long id) {
        try {
            return ResultUtil.success(resourceService.selectByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result findList(@RequestBody Resource resource,@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
        try {
            return ResultUtil.success(resourceService.findList(resource,pageNumber,pageSize));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
}
