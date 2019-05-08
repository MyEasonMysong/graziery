/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.ResourceVar;
import com.thinkgem.jeesite.modules.sys.dao.SysAnnexDao;
import com.thinkgem.jeesite.modules.sys.entity.SysAnnex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 附件生成Service
 * @author ljx
 * @version 2018-07-27
 */
@Service
@Transactional(readOnly = true)
public class SysAnnexService extends CrudService<SysAnnexDao, SysAnnex> {

	private String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("HHmmssSSS");
		return fmt.format(new Date());
	}
	
	public SysAnnex get(String id) {
		return super.get(id);
	}
	
	public List<SysAnnex> findList(SysAnnex sysAnnex) {
		return super.findList(sysAnnex);
	}
	
	public Page<SysAnnex> findPage(Page<SysAnnex> page, SysAnnex sysAnnex) {
		return super.findPage(page, sysAnnex);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAnnex sysAnnex) {
		super.save(sysAnnex);
	}
	
	/**
	* 
	* @Title: delete 
	* @author: LJX
	* @date: 2018年7月27日 下午6:50:40 
	* @Description: 通过id删除文件
	* @param: sysAnnex 
	* @return: void
	* @throws：
	 */
	@Transactional(readOnly = false)
	public void delete(SysAnnex sysAnnex) {
		super.delete(sysAnnex);
	}
	
	/**
	* 
	* @Title: deleteBytableId 
	* @author: LJX
	* @date: 2018年7月27日 下午6:50:40 
	* @Description: 通过tableid 和tableName删除文件
	* @param: sysAnnex 
	* @return: void
	* @throws：
	 */
	@Transactional(readOnly = false)
	public void deleteBytableId(SysAnnex sysAnnex) {
		dao.deleteBytableId(sysAnnex);
	}
	/**
	* 
	* @Title: upload 
	* @author: sy
	* @date: 2018年8月27日 下午1:50:40 
	* @Description: 上传文件
	* @param: sysAnnex 
	* @return: SysAnnex
	* @throws：
	 */
	@ResponseBody
	@Transactional(readOnly = false)
	public SysAnnex upload(MultipartFile file, String tblId, String tblName,String type, HttpServletRequest request, Long status) throws Exception {
		// 相对路径
		System.out.println(type);
		String destDir = File.separator + tblName + File.separator + tblId;
		SysAnnex att = new SysAnnex();
		try {
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			String realPath = ResourceVar.ANNEX_PATH;
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			att.setTableId(tblId);
			att.setTableName(tblName);
			att.setFilePath(destDir + File.separator + fileNameNew);
			att.setFileName(file.getOriginalFilename());
			att.setFileType(type);
			save(att);
		} catch (Exception e) {
			throw e;
		}
		return att;
	}
	
	/**
	* 
	* @Title: save 
	* @author: sy
	* @date: 2018年8月27日 下午1:40:12 
	* @Description: 保存附件信息 
	* @param: tableName
	* @param: tableId
	* @param: sysAnnex
	* @return: void
	* @throws：
	 */
	@Transactional(readOnly = false)
	public void save(String tableName, String tableId, SysAnnex sysAnnex) {
		sysAnnex.setTableName(tableName);
		sysAnnex.setTableId(tableId);
		this.save(sysAnnex);
	}
	
}