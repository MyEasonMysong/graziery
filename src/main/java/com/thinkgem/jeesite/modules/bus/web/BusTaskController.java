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
import com.thinkgem.jeesite.modules.bus.entity.BusTask;
import com.thinkgem.jeesite.modules.bus.service.BusTaskService;

/**
 * 专项任务Controller
 * @author zmy
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/bus/busTask")
public class BusTaskController extends BaseController {

	@Autowired
	private BusTaskService busTaskService;
	
	@ModelAttribute
	public BusTask get(@RequestParam(required=false) String id) {
		BusTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busTaskService.get(id);
		}
		if (entity == null){
			entity = new BusTask();
		}
		return entity;
	}

	/**
	 * @Title: list
	 * @author: ZMY
	 * @date: 2019年03月27日18:33:44
	 * @Description: 专项任务管理-列表
	 * @param:
	 * @return
	 */
	@RequiresPermissions("bus:busTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusTask busTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusTask> page = busTaskService.findPage(new Page<BusTask>(request, response), busTask); 
		model.addAttribute("page", page);
		return "modules/bus/busTaskList";
	}

	/**
	 * @Title: form
	 * @author: ZMY
	 * @date: 2019年03月27日18:33:44
	 * @Description: 专项任务管理-新增
	 * @param:
	 * @return
	 */
	@RequiresPermissions("bus:busTask:view")
	@RequestMapping(value = "form")
	public String form(BusTask busTask, Model model) {
		model.addAttribute("busTask", busTask);
		return "modules/bus/busTaskForm";
	}

	@RequiresPermissions("bus:busTask:edit")
	@RequestMapping(value = "save")
	public String save(BusTask busTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busTask)){
			return form(busTask, model);
		}
		busTaskService.save(busTask);
		addMessage(redirectAttributes, "保存专项任务成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busTask/?repage";
	}
	
	@RequiresPermissions("bus:busTask:edit")
	@RequestMapping(value = "delete")
	public String delete(BusTask busTask, RedirectAttributes redirectAttributes) {
		busTaskService.delete(busTask);
		addMessage(redirectAttributes, "删除专项任务成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busTask/?repage";
	}

}