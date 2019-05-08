package com.sxkj.gaia.api;

import com.sxkj.base.BaseController;
import com.sxkj.gaia.model.Role;
import com.sxkj.gaia.service.RoleService;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.sxkj.gaia.api.RoleController
 *
 * @author zwd
 * @Description RoleController
 * @Date Create in 2018-07-20 0020 15:38
 * @Modified
 */
@Api(value = "角色管理",description = "角色管理")
@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController<Role>{
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色绑定用户")
    @PostMapping("/bindUsers")
    public Result bindUsers(@RequestParam Long roleId, @RequestParam List<Long> userIds){
        try {
            return ResultUtil.success(roleService.bindUsers(roleId, userIds));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
    @ApiOperation(value = "角色绑定资源")
    @PostMapping("/bindResources")
    public Result bindResources(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        try {
            return ResultUtil.success(roleService.bindResources(roleId, resourceIds));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
    @ApiOperation(value = "角色明细数据范围配置")
    @PostMapping("/bindOrgs")
    public Result bindOrgs(@RequestParam Long roleId,@RequestParam List<Long> orgIds){
        try {
            return ResultUtil.success(roleService.bindOrg(roleId, orgIds));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result insert(@RequestBody Role role) {
        try {
            return ResultUtil.success(roleService.insert(role));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result delete(@PathVariable Long id) {
        try {
            return ResultUtil.success(roleService.deleteByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result update(@RequestBody Role role) {
        try {
            return ResultUtil.success(roleService.updateByPrimaryKey(role));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result select(@PathVariable Long id) {
        try {
            return ResultUtil.success(roleService.selectByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result findList(@RequestParam Role role, @PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
        try {
            return ResultUtil.success(roleService.findList(role,pageNumber,pageSize));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
}
