/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.ResourceVar;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.SysAnnex;
import com.thinkgem.jeesite.modules.sys.service.SysAnnexService;

/**
 * 附件生成Controller
 * @author ljx
 * @version 2018-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysAnnex")
public class SysAnnexController extends BaseController {

	@Autowired
	private SysAnnexService sysAnnexService;
	
	@ModelAttribute
	public SysAnnex get(@RequestParam(required=false) String id) {
		SysAnnex entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAnnexService.get(id);
		}
		if (entity == null){
			entity = new SysAnnex();
		}
		return entity;
	}
	/**
	* 
	* @Title: upload 
	* @author: LJX
	* @date: 2018年7月27日 下午6:46:43 
	* @Description: 文件上传
	* @param: sysAnnex
	* @return: sysAnnex
	* @throws：
	 */
	@RequestMapping(value = { "upload" })
	public @ResponseBody SysAnnex upload(@RequestParam("file") MultipartFile file, SysAnnex sysAnnex, HttpServletRequest request,
	        HttpServletResponse response, Long status) {
		SysAnnex att = new SysAnnex();
		try {
			att = sysAnnexService.upload(file, sysAnnex.getTableId(), sysAnnex.getTableName(), sysAnnex.getFileType(),request, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return att;
	}
	
	@RequestMapping(value = { "download" })
	public void download(String id, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		SysAnnex att = sysAnnexService.get(id);
		String fileName = att.getFileName();
		try {
			String basepath = String.valueOf(ResourceVar.ANNEX_PATH + att.getFilePath());
			File file = new File(basepath);
			FileUtils.downFile(file, request, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* 
	* @Title: delete 
	* @author: LJX
	* @date: 2018年7月27日 下午6:46:43 
	* @Description: 删除附件
	* @param: sysAnnex
	* @return: String
	* @throws：
	 */
	@RequestMapping(value = { "delete" })
	public String  delete(SysAnnex sysAnnex) {
		 sysAnnexService.delete(sysAnnex);
		return sysAnnex.getId();
	}
	
	/**
	* 
	* @Title: searchList 
	* @author: LJX
	* @date: 2018年7月27日 下午6:55:02 
	* @Description: 查询下载列表
	* @param: sysAnnex
	* @return: List<SysAnnex>
	* @throws：
	 */
	@RequestMapping(value = { "searchList" })
	public @ResponseBody List<SysAnnex> searchList(SysAnnex sysAnnex) {
		List<SysAnnex> list = sysAnnexService.findList(sysAnnex);
		return list;
	}

}