/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.bas.entity.Item;
import com.thinkgem.jeesite.modules.bas.service.ItemService;
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
import com.thinkgem.jeesite.modules.bas.entity.CheckSystem;
import com.thinkgem.jeesite.modules.bas.service.CheckSystemService;

import java.util.List;

/**
 * 检查体系表生成Controller
 * @author sr
 * @version 2019-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/checkSystem")
public class CheckSystemController extends BaseController {

	@Autowired
	private CheckSystemService checkSystemService;

	@Autowired
	private ItemService itemService;

	@ModelAttribute
	public CheckSystem get(@RequestParam(required=false) String id) {
		CheckSystem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = checkSystemService.get(id);
		}
		if (entity == null){
			entity = new CheckSystem();
		}
		return entity;
	}
	
	@RequiresPermissions("bas:checkSystem:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckSystem checkSystem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CheckSystem> page = checkSystemService.findPage(new Page<CheckSystem>(request, response), checkSystem); 
		model.addAttribute("page", page);
		return "modules/bas/checkSystemList";
	}

	@RequiresPermissions("bas:checkSystem:view")
	@RequestMapping(value = "form")
	public String form(CheckSystem checkSystem, Model model) {
		List<Item> itemList = itemService.findAllList(new Item(),checkSystem.getId());
		model.addAttribute("checkSystem", checkSystem);
		model.addAttribute("itemList", itemList);
		return "modules/bas/checkSystemForm";
	}

	@RequiresPermissions("bas:checkSystem:edit")
	@RequestMapping(value = "save")
	public String save(CheckSystem checkSystem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, checkSystem)){
			return form(checkSystem, model);
		}
		checkSystemService.save(checkSystem);
		addMessage(redirectAttributes, "保存检查体系表成功");
		return "redirect:"+Global.getAdminPath()+"/bas/checkSystem/?repage";
	}
	
	@RequiresPermissions("bas:checkSystem:edit")
	@RequestMapping(value = "delete")
	public String delete(CheckSystem checkSystem, RedirectAttributes redirectAttributes) {
		checkSystemService.delete(checkSystem);
		addMessage(redirectAttributes, "删除检查体系表成功");
		return "redirect:"+Global.getAdminPath()+"/bas/checkSystem/?repage";
	}
	@RequiresPermissions("bas:checkSystem:view")
	@RequestMapping(value = "view")
	public String view(CheckSystem checkSystem, Model model) {
		List<Item> itemsList = itemService.findViewList(new Item(),checkSystem.getId());
		model.addAttribute("checkSystem", checkSystem);
		model.addAttribute("itemsList", itemsList);
		return "modules/bas/checkSystemView";
	}

	/**
	 * @Title: selectCheckSystem
	 * @author: ZMY
	 * @date: 2019年03月27日19:33:44
	 * @Description: 专项任务管理-新增-选择指标体系
	 * @param:
	 * @return
	 */
	@RequiresPermissions("bas:checkSystem:view")
	@RequestMapping(value = "selectCheckSystem")
	public String selectCheckSystem(CheckSystem checkSystem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CheckSystem> page = checkSystemService.findPage(new Page<CheckSystem>(request, response), checkSystem);
		model.addAttribute("page", page);
		return "modules/bas/checkSystemSelectList";
	}
}