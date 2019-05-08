package com.sxkj.gaia.api;

import com.github.pagehelper.PageInfo;
import com.sxkj.base.BaseController;
import com.sxkj.gaia.model.User;
import com.sxkj.gaia.service.UserService;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * com.sxkj.gaia.api.UserController
 *
 * @author zwd
 * @Description UserController
 * @Date Create in 2018-07-20 0020 9:10
 * @Modified
 */
@Api(tags = {"用户管理"})
@RequestMapping("/api/user")
@RestController
public class UserController extends BaseController<User> {
    @Autowired
    private UserService userService;

    /**
     * 修改用户密码，此功能只能修改用户的登录密码，与修改用户信息不同
     * @param loginName
     * @param loginPwd
     * @return
     */
    @ApiOperation(value = "修改密码")
    @PostMapping("/editPwd")
    //@Permission("self.test")
    public Result updatePassword(@RequestParam("loginName") String loginName,@RequestParam("loginPwd") String loginPwd){
        try {
            return ResultUtil.success(userService.updatePassword(loginName, loginPwd));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    /**
     * 为用户绑定角色，可同时绑定对个角色
     * @param userId
     * @param roleIds
     * @return
     */
    @ApiOperation(value = "为用户绑定角色")
    @PostMapping("/bindRole")
    public Result bindRoles(@RequestParam Long userId, @RequestParam List<Long> roleIds) {
        try {
            return ResultUtil.success(userService.bindRoles(userId, roleIds));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result insert(@RequestBody User user) {
        try {
            return ResultUtil.success(userService.insert(user));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result delete(@PathVariable Long id) {
        try {
            return ResultUtil.success(userService.deleteByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    /**
     * 修改除登录密码之外的信息
     * @param user
     * @return
     */
    @Override
    public Result update(@RequestBody User user) {
        try {
            return ResultUtil.success(userService.updateByPrimaryKey(user));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result select(@PathVariable Long id) {
        try {
            return ResultUtil.success(userService.selectByPrimaryKey(id));
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }

    @Override
    public Result findList(@RequestBody User user,@PathVariable Integer pageNumber,@PathVariable Integer pageSize) {
        try {
            PageInfo pageInfo = userService.findList(user, pageNumber, pageSize);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
}
