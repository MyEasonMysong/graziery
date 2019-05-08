/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.thinkgem.jeesite.common.config.GlobalConst;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bas.entity.Ent;
import com.thinkgem.jeesite.modules.bas.service.EntService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.service.AreaService;

/**
 * 企业表生成Controller
 * 
 * @author sr
 * @version 2019-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/ent")
public class EntController extends BaseController {

    @Autowired
    private EntService entService;
    @Autowired
    private AreaService areaService;
    @ModelAttribute
    public Ent get(@RequestParam(required = false) String id) {
	Ent entity = null;
	if (StringUtils.isNotBlank(id)) {
	    entity = entService.get(id);
	}
	if (entity == null) {
	    entity = new Ent();
	}
	return entity;
    }

    @RequiresPermissions("bas:ent:view")
    @RequestMapping(value = { "list", "" })
    public String list(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model) {
	Area areaParent=new Area();
	areaParent.setId(GlobalConst.AREA_SY_ID);
	Area areaQuery=new Area();
	areaQuery.setParent(areaParent);
	List<Area> areaList=areaService.findAllByParentId(areaQuery);
	model.addAttribute("areaList", areaList);
	Page<Ent> page = entService.findPage(new Page<Ent>(request, response), ent);
	model.addAttribute("page", page);
	return "modules/bas/entList";
    }

    @RequiresPermissions("bas:ent:view")
    @RequestMapping(value = "form")
    public String form(Ent ent, Model model) {
	Area areaParent=new Area();
	areaParent.setId(GlobalConst.AREA_SY_ID);
	Area areaQuery=new Area();
	areaQuery.setParent(areaParent);
	List<Area> areaList=areaService.findAllByParentId(areaQuery);
	model.addAttribute("areaList", areaList);
	model.addAttribute("ent", ent);
	return "modules/bas/entForm";
    }

    @RequiresPermissions("bas:ent:edit")
    @RequestMapping(value = "save")
    public String save(Ent ent, Model model, RedirectAttributes redirectAttributes) {
	if (!beanValidator(model, ent)) {
	    return form(ent, model);
	}
	entService.save(ent);
	addMessage(redirectAttributes, "保存企业表成功");
	return "redirect:" + Global.getAdminPath() + "/bas/ent/?repage";
    }

    @RequiresPermissions("bas:ent:edit")
    @RequestMapping(value = "delete")
    public String delete(Ent ent, RedirectAttributes redirectAttributes) {
	entService.delete(ent);
	addMessage(redirectAttributes, "删除企业表成功");
	return "redirect:" + Global.getAdminPath() + "/bas/ent/?repage";
    }
    @RequiresPermissions("bas:ent:view")
    @RequestMapping(value = "view")
    public String view(Ent ent, Model model) {
	model.addAttribute("ent", ent);
	return "modules/bas/entView";
    }
    /**
     * 		
        * @Title: qrView
        * @Description: 企业档案-二维码生成-列表
        * lxy
        * 2019年1月28日13:45:24
        * @param @param ent
        * @param @param request
        * @param @param response
        * @param @param model
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequiresPermissions("bas:ent:view")
    @RequestMapping(value = "qrList")
    public String qrList(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model) {
	Area areaParent=new Area();
	areaParent.setId(GlobalConst.AREA_SY_ID);
	Area areaQuery=new Area();
	areaQuery.setParent(areaParent);
	List<Area> areaList=areaService.findAllByParentId(areaQuery);
	model.addAttribute("areaList", areaList);
	Page<Ent> page = entService.findPage(new Page<Ent>(request, response), ent);
	model.addAttribute("page", page);
	return "modules/bas/entQrList";
    }
    /**
     * 		
        * @Title: qrView
        * @Description: 企业档案-二维码生成-查看
        * lxy
        * 2019年1月28日13:45:24
        * @param @param ent
        * @param @param request
        * @param @param response
        * @param @param model
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequiresPermissions("bas:ent:view")
    @RequestMapping(value = "qrView")
    public String qrView(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model) {
	String fileName=entService.createQrCode(ent);
	model.addAttribute("fileName", fileName);
	model.addAttribute("ent", ent);
	return "modules/bas/entQrView";
    }
    /**
        * @Title: qrView
        * @Description: 企业档案-二维码生成-查看-下载二维码
        * lxy
        * 2019年1月28日13:45:24
        * @param @param ent
        * @param @param request
        * @param @param response
        * @param @param model
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequiresPermissions("bas:ent:down")
    @RequestMapping(value = "downQrCode")
    public void downQrCode(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model){
	String attachmentPath = Global.getAttachmentPath();
	String directoryPath=attachmentPath+"\\ent_qr";
	String fileName =ent.getId()+".png";
	String filePath =directoryPath+"\\"+fileName;
	File file = new File(filePath);
	FileUtils.downFile(file, request, response,ent.getName()+".png");
    }
    /**
        * @Title: qrView
        * @Description: 企业档案-二维码生成-批量下载二维码
        * lxy
        * 2019年1月28日13:45:24
        * @param @param ent
        * @param @param request
        * @param @param response
        * @param @param model
        * @param @return    参数
        * @return String    返回类型
        * @throws
     */
    @RequiresPermissions("bas:ent:down")
    @RequestMapping(value = "batchDownQrCode")
    public void batchDownQrCode(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model) {
	//创建临时文件夹并生成二维码
	String timeFolder=this.entService.createTempQrCode(ent);
	String attachmentPath = Global.getAttachmentPath();
	String directoryPath=attachmentPath+"\\ent_qr_temp\\"+timeFolder;
	String zipPath=attachmentPath+"\\ent_qr_temp\\"+timeFolder+".zip";
	//生成压缩文件
	FileUtils.zipFiles(directoryPath,"",zipPath);
	//删除临时文件夹
	FileUtils.deleteDirectory(directoryPath);
	//下载
	File file = new File(zipPath);
	FileUtils.downFile(file, request, response,"企业二维码.zip");
    }

	/**
	 * @Title: selectForPlan
	 * @author: ZMY
	 * @date: 2019年03月27日14:12:44
	 * @Description: 年度检查计划-新增-选择计划对象
	 * @param:
	 * @return
	 */
	@RequiresPermissions("bas:ent:view")
	@RequestMapping(value = "selectForPlan")
	public String selectForPlan(Ent ent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Area areaParent=new Area();
		areaParent.setId(GlobalConst.AREA_SY_ID);
		Area areaQuery=new Area();
		areaQuery.setParent(areaParent);
		List<Area> areaList=areaService.findAllByParentId(areaQuery);
		model.addAttribute("areaList", areaList);
		Page<Ent> page = entService.findPage(new Page<Ent>(request, response), ent);
		model.addAttribute("page", page);
		return "modules/bas/entSelectForPlan";
	}
}