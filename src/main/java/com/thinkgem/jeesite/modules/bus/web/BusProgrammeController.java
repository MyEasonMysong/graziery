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
import com.thinkgem.jeesite.modules.bus.entity.BusProgramme;
import com.thinkgem.jeesite.modules.bus.service.BusProgrammeService;

/**
 * 现场检查方案Controller
 * @author wl
 * @version 2019-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/bus/busProgramme")
public class BusProgrammeController extends BaseController {

	@Autowired
	private BusProgrammeService busProgrammeService;
	
	@ModelAttribute
	public BusProgramme get(@RequestParam(required=false) String id) {
		BusProgramme entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busProgrammeService.get(id);
		}
		if (entity == null){
			entity = new BusProgramme();
		}
		return entity;
	}
	
	@RequiresPermissions("bus:busProgramme:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusProgramme busProgramme, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusProgramme> page = busProgrammeService.findPage(new Page<BusProgramme>(request, response), busProgramme); 
		model.addAttribute("page", page);
		return "modules/bus/busProgrammeList";
	}

	@RequiresPermissions("bus:busProgramme:view")
	@RequestMapping(value = "form")
	public String form(BusProgramme busProgramme, Model model) {
		model.addAttribute("busProgramme", busProgramme);
		return "modules/bus/busProgrammeForm";
	}

	@RequiresPermissions("bus:busProgramme:edit")
	@RequestMapping(value = "save")
	public String save(BusProgramme busProgramme, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busProgramme)){
			return form(busProgramme, model);
		}
		busProgrammeService.save(busProgramme);
		addMessage(redirectAttributes, "保存现场检查方案成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busProgramme/?repage";
	}
	
	@RequiresPermissions("bus:busProgramme:edit")
	@RequestMapping(value = "delete")
	public String delete(BusProgramme busProgramme, RedirectAttributes redirectAttributes) {
		busProgrammeService.delete(busProgramme);
		addMessage(redirectAttributes, "删除现场检查方案成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busProgramme/?repage";
	}

}