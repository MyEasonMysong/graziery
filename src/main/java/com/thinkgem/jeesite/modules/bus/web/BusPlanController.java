/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bus.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.bus.entity.BusPlan;
import com.thinkgem.jeesite.modules.bus.service.BusPlanService;


/**
 * 检查计划Controller
 * @author sr
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/bus/busPlan")
public class BusPlanController extends BaseController {

	@Autowired
	private BusPlanService busPlanService;

	@ModelAttribute
	public BusPlan get(@RequestParam(required=false) String id) {
		BusPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = busPlanService.get(id);
		}
		if (entity == null){
			entity = new BusPlan();
		}
		return entity;
	}

	/**
	 * @Title: list
	 * @author: ZMY
	 * @date: 2019年03月27日10:33:44
	 * @Description: 年度检查计划-列表
	 * @param:
	 * @return
	 */
	@RequiresPermissions("bus:busPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(BusPlan busPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BusPlan> page = busPlanService.findPage(new Page<BusPlan>(request, response), busPlan); 
		model.addAttribute("page", page);
		return "modules/bus/busPlanList";
	}

    /**
     * @Title: form
     * @author: ZMY
     * @date: 2019年03月27日10:59:44
     * @Description: 年度检查计划-新增
     * @param:
     * @return
     */
	@RequiresPermissions("bus:busPlan:view")
	@RequestMapping(value = "form")
	public String form(BusPlan busPlan, Model model) {
		model.addAttribute("busPlan", busPlan);
        User user = UserUtils.getUser();
        if(null!=user){
            model.addAttribute("officeName", user.getOffice().getName());
        }
		return "modules/bus/busPlanForm";
	}

	@RequiresPermissions("bus:busPlan:edit")
	@RequestMapping(value = "save")
	public String save(BusPlan busPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, busPlan)){
			return form(busPlan, model);
		}
		busPlanService.save(busPlan);
		addMessage(redirectAttributes, "保存检查计划成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busPlan/?repage";
	}
	
	@RequiresPermissions("bus:busPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(BusPlan busPlan, RedirectAttributes redirectAttributes) {
		busPlanService.delete(busPlan);
		addMessage(redirectAttributes, "删除检查计划成功");
		return "redirect:"+Global.getAdminPath()+"/bus/busPlan/?repage";
	}

}