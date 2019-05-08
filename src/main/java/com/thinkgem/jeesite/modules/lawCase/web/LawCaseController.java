/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lawCase.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.lawCase.entity.LawCase;
import com.thinkgem.jeesite.modules.lawCase.service.LawCaseService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案件Controller
 * @author WangLe
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/lowCase")
public class LawCaseController extends BaseController {

	@Autowired
	private LawCaseService lawCaseService;
	
	@ModelAttribute
	public LawCase get(@RequestParam(required=false) String id) {
		LawCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lawCaseService.get(id);
		}
		if (entity == null){
			entity = new LawCase();
		}
		return entity;
	}

	/**
	 *
	 * @title register
	 * @author WangLe
	 * @date 2019年03月27日 下午 05:00:04
	 * @description TODO 进入立案审批界面
	 * @param lawCase,request,response,model
	 * @return String
	 */
	@RequiresPermissions("lawCase:view")
	@RequestMapping(value = "register")
	public String register(LawCase lawCase, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<LawCase> page = lowCaseService.findPage(new Page<LawCase>(request, response), lawCase);
//		model.addAttribute("page", page);
		return "modules/lawCase/lowCaseRegister";
	}

	/**
	 * @Title: list
	 * @author: WangLe
	 * @date: 2019年03月27日10:33:44
	 * @Description: 年度检查计划-列表
	 * @param:
	 * @return
	 */
	@RequiresPermissions("lawCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(LawCase lawCase, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<LawCase> page = lowCaseService.findPage(new Page<LawCase>(request, response), lawCase);
//		model.addAttribute("page", page);
		return "modules/lawCase/lowCaseList";
	}

    /**
     * @Title: form
     * @author: ZMY
     * @date: 2019年03月27日10:59:44
     * @Description: 年度检查计划-新增
     * @param:
     * @return
     */
	@RequiresPermissions("lawCase:view")
	@RequestMapping(value = "form")
	public String form(LawCase lawCase, Model model) {
		model.addAttribute("lowCase", lawCase);
        User user = UserUtils.getUser();
        if(null!=user){
            model.addAttribute("officeName", user.getOffice().getName());
        }
		return "modules/bus/lowCaseForm";
	}

	@RequiresPermissions("lawCase:edit")
	@RequestMapping(value = "save")
	public String save(LawCase lawCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, lawCase)){
			return form(lawCase, model);
		}
		lawCaseService.save(lawCase);
		addMessage(redirectAttributes, "保存检查计划成功");
		return "redirect:"+Global.getAdminPath()+"/bus/lawCase/?repage";
	}
	
	@RequiresPermissions("lawCase:edit")
	@RequestMapping(value = "delete")
	public String delete(LawCase lawCase, RedirectAttributes redirectAttributes) {
		lawCaseService.delete(lawCase);
		addMessage(redirectAttributes, "删除检查计划成功");
		return "redirect:"+Global.getAdminPath()+"/bus/lawCase/?repage";
	}

}