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
import com.thinkgem.jeesite.modules.bas.entity.OvertimePlan;
import com.thinkgem.jeesite.modules.bas.service.OvertimePlanService;

/**
 * 加班申请Controller
 * @author lichao
 * @version 2019-05-15
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/overtimePlan")
public class OvertimePlanController extends BaseController {

	@Autowired
	private OvertimePlanService overtimePlanService;
	
	@ModelAttribute
	public OvertimePlan get(@RequestParam(required=false) String id) {
		OvertimePlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = overtimePlanService.get(id);

		}
		if (entity == null){
			entity = new OvertimePlan();
		}
		return entity;
	}
	
	@RequiresPermissions("bas:overtimePlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(OvertimePlan overtimePlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OvertimePlan> page = overtimePlanService.findPage(new Page<OvertimePlan>(request, response), overtimePlan); 
		model.addAttribute("page", page);
		return "modules/bas/overtimePlanList";
	}

	@RequiresPermissions("bas:overtimePlan:view")
	@RequestMapping(value = "form")
	public String form(OvertimePlan overtimePlan, Model model) {
		model.addAttribute("overtimePlan", overtimePlan);
		return "modules/bas/overtimePlanForm";
	}

	@RequiresPermissions("bas:overtimePlan:edit")
	@RequestMapping(value = "save")
	public String save(OvertimePlan overtimePlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, overtimePlan)){
			return form(overtimePlan, model);
		}
		overtimePlanService.save(overtimePlan);
		addMessage(redirectAttributes, "保存加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/bas/overtimePlan/?repage";
	}
	
	@RequiresPermissions("bas:overtimePlan:edit")
	@RequestMapping(value = "delete")
	public String delete(OvertimePlan overtimePlan, RedirectAttributes redirectAttributes) {
		overtimePlanService.delete(overtimePlan);
		addMessage(redirectAttributes, "删除加班申请成功");
		return "redirect:"+Global.getAdminPath()+"/bas/overtimePlan/?repage";
	}

}