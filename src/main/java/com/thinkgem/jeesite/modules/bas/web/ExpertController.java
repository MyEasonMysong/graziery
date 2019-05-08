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
import com.thinkgem.jeesite.modules.bas.entity.Expert;
import com.thinkgem.jeesite.modules.bas.service.ExpertService;

/**
 * 专家库生成Controller
 * 
 * @author sr
 * @version 2019-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/expert")
public class ExpertController extends BaseController {

    @Autowired
    private ExpertService expertService;

    @ModelAttribute
    public Expert get(@RequestParam(required = false) String id) {
	Expert entity = null;
	if (StringUtils.isNotBlank(id)) {
	    entity = expertService.get(id);
	}
	if (entity == null) {
	    entity = new Expert();
	}
	return entity;
    }

    @RequiresPermissions("bas:expert:view")
    @RequestMapping(value = { "list", "" })
    public String list(Expert expert, HttpServletRequest request, HttpServletResponse response, Model model) {
	Page<Expert> page = expertService.findPage(new Page<Expert>(request, response), expert);
	model.addAttribute("page", page);
	return "modules/bas/expertList";
    }

    @RequiresPermissions("bas:expert:view")
    @RequestMapping(value = "form")
    public String form(Expert expert, Model model) {
	model.addAttribute("expert", expert);
	return "modules/bas/expertForm";
    }

    @RequiresPermissions("bas:expert:edit")
    @RequestMapping(value = "save")
    public String save(Expert expert, Model model, RedirectAttributes redirectAttributes) {
	if (!beanValidator(model, expert)) {
	    return form(expert, model);
	}
	expertService.save(expert);
	addMessage(redirectAttributes, "保存专家库成功");
	return "redirect:" + Global.getAdminPath() + "/bas/expert/?repage";
    }

    @RequiresPermissions("bas:expert:edit")
    @RequestMapping(value = "delete")
    public String delete(Expert expert, RedirectAttributes redirectAttributes) {
	expertService.delete(expert);
	addMessage(redirectAttributes, "删除专家库成功");
	return "redirect:" + Global.getAdminPath() + "/bas/expert/?repage";
    }

    @RequiresPermissions("bas:expert:view")
    @RequestMapping(value = "view")
    public String view(Expert expert, Model model) {
	model.addAttribute("expert", expert);
	return "modules/bas/expertView";
    }

}