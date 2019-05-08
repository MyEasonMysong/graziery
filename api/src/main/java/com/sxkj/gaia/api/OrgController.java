package com.sxkj.gaia.api;

import com.github.pagehelper.PageInfo;
import com.sxkj.base.BaseController;
import com.sxkj.gaia.model.Org;
import com.sxkj.gaia.service.OrgService;
import com.sxkj.util.Result;
import com.sxkj.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * com.sxkj.gaia.api.OrgController
 *
 * @author zwd
 * @Description OrgController
 * @Date Create in 2018-06-13 0013 13:43
 * @Modified
 */
@Api(tags = {"机构管理"})
@RestController
@RequestMapping("/api/org")
public class OrgController extends BaseController<Org> {
    @Autowired
    private OrgService orgService;

    @Override
    public Result insert(@RequestBody Org org) {
        Result result;
        try {
            Long id = orgService.insert(org);
            result = ResultUtil.success(id);
        } catch (Exception e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }

    @Override
    public Result delete(@PathVariable Long id) {
        Result result;
        try {
            result = ResultUtil.success(orgService.deleteByPrimaryKey(id));
        } catch (Exception e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }

    @Override
    public Result update(@RequestBody Org org) {
        Result result;
        try {
            result = ResultUtil.success(orgService.updateByPrimaryKey(org));
        } catch (Exception e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }

    @Override
    public Result select(@PathVariable Long id) {
        Result result;
        try {
            result = ResultUtil.success(orgService.selectByPrimaryKey(id));
        } catch (Exception e) {
            result = exceptionHandle.exceptionGet(e);
        }
        return result;
    }

    @ApiOperation(value = "根据条件查询")
    @PostMapping(value = "/{pageNumber}/{pageSize}",produces = "application/json;charset=UTF-8")
    @Override
    public Result findList(@RequestBody Org org,@PathVariable Integer pageNumber,@PathVariable Integer pageSize){
        try {
            PageInfo pageInfo = orgService.findList(org, pageNumber, pageSize);
            return ResultUtil.success(pageInfo);
        } catch (Exception e) {
            return exceptionHandle.exceptionGet(e);
        }
    }
}
