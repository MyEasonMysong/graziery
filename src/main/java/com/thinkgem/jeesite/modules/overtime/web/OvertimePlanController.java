/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.overtime.web;

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
import com.thinkgem.jeesite.modules.overtime.entity.OvertimePlan;
import com.thinkgem.jeesite.modules.overtime.service.OvertimePlanService;

/**
 * 加班计划申请Controller
 * @author Xuyanyan
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/overtime/overtimePlan")
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
	
	@RequiresPermissions("overtime:overtimePlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(OvertimePlan overtimePlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OvertimePlan> page = overtimePlanService.findPage(new Page<OvertimePlan>(request, response), overtimePlan); 
		model.addAttribute("page", page);
		return "modules/overtime/overtimePlanList";
	}

	@RequiresPermissions("overtime:overtimePlan:view")
	@RequestMapping(value = "form")
	public String form(OvertimePlan overtimePlan, Model model) {

		String view = "overtimePlanForm";

		// 查看审批申请单
		if (org.apache.commons.lang3.StringUtils.isNotBlank(overtimePlan.getId())){//.getAct().getProcInsId())){

			// 环节编号
			String taskDefKey = overtimePlan.getAct().getTaskDefKey();

			// 查看工单
			if(overtimePlan.getAct().isFinishTask()){
				view = "testAuditView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "testAuditForm";
			}
			// 审核环节
			else if ("audit".equals(taskDefKey)){
				view = "testAuditAudit";
//				String formKey = "/oa/testAudit";
//				return "redirect:" + ActUtils.getFormUrl(formKey, testAudit.getAct());
			}
			// 审核环节2
			else if ("audit2".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 审核环节3
			else if ("audit3".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 审核环节4
			else if ("audit4".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 兑现环节
			else if ("apply_end".equals(taskDefKey)){
				view = "testAuditAudit";
			}
		}
		model.addAttribute("overtimePlan", overtimePlan);
		return "modules/overtime/"+view;
	}

	@RequiresPermissions("overtime:overtimePlan:edit")
	@RequestMapping(value = "save")
	public String save(OvertimePlan overtimePlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, overtimePlan)){
			return form(overtimePlan, model);
		}
		overtimePlanService.save(overtimePlan);
		addMessage(redirectAttributes, "保存加班计划申请成功");
		return "redirect:"+Global.getAdminPath()+"/overtime/overtimePlan/?repage";
	}
	
	@RequiresPermissions("overtime:overtimePlan:edit")
	@RequestMapping(value = "delete")
	public String delete(OvertimePlan overtimePlan, RedirectAttributes redirectAttributes) {
		overtimePlanService.delete(overtimePlan);
		addMessage(redirectAttributes, "删除加班计划申请成功");
		return "redirect:"+Global.getAdminPath()+"/overtime/overtimePlan/?repage";
	}

}