/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.web;

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
import com.thinkgem.jeesite.modules.bus.entity.BusPlanDetail;
import com.thinkgem.jeesite.modules.bus.service.BusPlanDetailService;

/**
 * 检查计划子表Controller
 * @author sr
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/bus/busPlanDetail")
public class BusPlanDetailController extends BaseController {

	@Autowired
	private BusPlanDetailService busPlanDetailService;
	
	@ModelAttribute
	public BusPlanDetail get(@RequestParam(required=false) String id) {
		BusPlanDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busPlanDetailService.get(id);
		}
		if (entity == null){
			entity = new BusPlanDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("bus:busPlanDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusPlanDetail busPlanDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusPlanDetail> page = busPlanDetailService.findPage(new Page<BusPlanDetail>(request, response), busPlanDetail); 
		model.addAttribute("page", page);
		return "modules/bus/busPlanDetailList";
	}

	@RequiresPermissions("bus:busPlanDetail:view")
	@RequestMapping(value = "form")
	public String form(BusPlanDetail busPlanDetail, Model model) {
		model.addAttribute("busPlanDetail", busPlanDetail);
		return "modules/bus/busPlanDetailForm";
	}

	@RequiresPermissions("bus:busPlanDetail:edit")
	@RequestMapping(value = "save")
	public String save(BusPlanDetail busPlanDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busPlanDetail)){
			return form(busPlanDetail, model);
		}
		busPlanDetailService.save(busPlanDetail);
		addMessage(redirectAttributes, "保存检查计划子表成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busPlanDetail/?repage";
	}
	
	@RequiresPermissions("bus:busPlanDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(BusPlanDetail busPlanDetail, RedirectAttributes redirectAttributes) {
		busPlanDetailService.delete(busPlanDetail);
		addMessage(redirectAttributes, "删除检查计划子表成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busPlanDetail/?repage";
	}

}