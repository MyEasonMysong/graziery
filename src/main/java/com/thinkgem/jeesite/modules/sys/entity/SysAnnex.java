/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 附件生成Entity
 * @author ljx
 * @version 2018-07-27
 */
public class SysAnnex extends DataEntity<SysAnnex> {
	
	private static final long serialVersionUID = 1L;
	private String tableId;		// 表ID
	private String tableName;		// 表名
	private String filePath;		// 路径信息
	private String fileType;		// 文件类型
	private String fileSize;		// 文件大小
	private String fileName;		// 文件名称
	
	public SysAnnex() {
		super();
	}

	public SysAnnex(String id){
		super(id);
	}

	@Length(min=0, max=64, message="表ID长度必须介于 0 和 64 之间")
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	@Length(min=0, max=50, message="表名长度必须介于 0 和 50 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Length(min=0, max=50, message="文件类型长度必须介于 0 和 50 之间")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=50, message="文件大小长度必须介于 0 和 50 之间")
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	@Length(min=0, max=100, message="文件名称长度必须介于 0 和 100 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}