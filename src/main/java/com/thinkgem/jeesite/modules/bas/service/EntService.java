/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bas.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.ZxingHandler;
import com.thinkgem.jeesite.modules.bas.entity.Ent;
import com.thinkgem.jeesite.modules.bas.dao.EntDao;

/**
 * 企业表生成Service
 * @author sr
 * @version 2019-01-23
 */
@Service
@Transactional(readOnly = true)
public class EntService extends CrudService<EntDao, Ent> {

	public Ent get(String id) {
		return super.get(id);
	}
	
	public List<Ent> findList(Ent ent) {
		return super.findList(ent);
	}
	
	public Page<Ent> findPage(Page<Ent> page, Ent ent) {
		return super.findPage(page, ent);
	}
	
	@Transactional(readOnly = false)
	public void save(Ent ent) {
		super.save(ent);
	}
	
	@Transactional(readOnly = false)
	public void delete(Ent ent) {
		super.delete(ent);
	}
	/**
	 * 2019年1月28日13:39:39
	 * lxy
	 * @Title: createQrCode
	 * @Description: 生成企业二维码
	 * @param @param ent
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public String createQrCode(Ent ent) {
	    String attachmentPath = Global.getAttachmentPath();
	    String directoryPath=attachmentPath+"\\ent_qr";
	    String fileName =ent.getId()+".png";
	    String filePath =directoryPath+"\\"+fileName;
	    File file = new File(filePath);
	    if (file.exists()) {
		logger.debug("文件 " + filePath + " 已存在!");
		return fileName; 
	    }
	    FileUtils.createDirectory(directoryPath);
	    int width = 300;
	    int height = 300;
	    ZxingHandler.encode2(ent.getId(), width, height, filePath);
	    return fileName;
	}
	/**
	 * 2019年1月28日13:39:39
	 * lxy
	 * @Title: createQrCode
	 * @Description: 生成企业二维码-生成企业二维码-临时文件夹
	 * @param @param ent
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	public String createTempQrCode(Ent ent) {
	    List<Ent> list=this.dao.findList(ent);
	    String attachmentPath = Global.getAttachmentPath();
	    String timeFolder=DateUtils.getDate("yyyyMMddHHmmss");
	    String directoryPath=attachmentPath+"\\ent_qr_temp\\"+timeFolder;
	    FileUtils.createDirectory(directoryPath);
	    String fileName="";
	    for(Ent e :list) {
		fileName =e.getName()+".png";
		String filePath =directoryPath+"\\"+fileName;
		int width = 300;
		int height = 300;
		ZxingHandler.encode2(e.getId(), width, height, filePath);
	    }
	    //返回生成的临时文件夹路径
	    return timeFolder;
	}
	
}