/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bas.entity.CheckSystemSub;
import com.thinkgem.jeesite.modules.bas.service.CheckSystemSubService;

/**
 * 检查体系-子表生成Controller
 * @author sr
 * @version 2019-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/checkSystemSub")
public class CheckSystemSubController extends BaseController {

	@Autowired
	private CheckSystemSubService checkSystemSubService;
	
	@ModelAttribute
	public CheckSystemSub get(@RequestParam(required=false) String id) {
		CheckSystemSub entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checkSystemSubService.get(id);
		}
		if (entity == null){
			entity = new CheckSystemSub();
		}
		return entity;
	}
	
	@RequiresPermissions("bas:checkSystemSub:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckSystemSub checkSystemSub, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CheckSystemSub> page = checkSystemSubService.findPage(new Page<CheckSystemSub>(request, response), checkSystemSub); 
		model.addAttribute("page", page);
		return "modules/bas/checkSystemSubList";
	}

	@RequiresPermissions("bas:checkSystemSub:view")
	@RequestMapping(value = "form")
	public String form(CheckSystemSub checkSystemSub, Model model) {
		model.addAttribute("checkSystemSub", checkSystemSub);
		return "modules/bas/checkSystemSubForm";
	}

	@RequiresPermissions("bas:checkSystemSub:edit")
	@RequestMapping(value = "save")
	public String save(CheckSystemSub checkSystemSub, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, checkSystemSub)){
			return form(checkSystemSub, model);
		}
		checkSystemSubService.save(checkSystemSub);
		addMessage(redirectAttributes, "保存检查体系-子表成功");
		return "redirect:"+Global.getAdminPath()+"/bas/checkSystemSub/?repage";
	}
	
	@RequiresPermissions("bas:checkSystemSub:edit")
	@RequestMapping(value = "delete")
	public String delete(CheckSystemSub checkSystemSub, RedirectAttributes redirectAttributes) {
		checkSystemSubService.delete(checkSystemSub);
		addMessage(redirectAttributes, "删除检查体系-子表成功");
		return "redirect:"+Global.getAdminPath()+"/bas/checkSystemSub/?repage";
	}

}