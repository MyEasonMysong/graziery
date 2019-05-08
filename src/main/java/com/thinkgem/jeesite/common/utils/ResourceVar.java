package com.thinkgem.jeesite.common.utils;

import java.util.ResourceBundle;

public class ResourceVar {

	public final static ResourceBundle BUNDLE = ResourceBundle.getBundle("resource");
	public static final String ANNEX_PATH = BUNDLE.getString("ANNEX_PATH"); // 附件地址
	public static final String PAGE_SIZE = BUNDLE.getString("PAGE_SIZE"); // 分页页数
	public static final String RESEAU_ROOT_NUMBER = BUNDLE.getString("RESEAU_ROOT_NUMBER"); // 服务器的省份
	public static final String RESEAU_ROOT_NAME = BUNDLE.getString("RESEAU_ROOT_NAME"); // 服务器的省份名称
	public static final Integer REPORT_DAYS = Integer.parseInt(BUNDLE.getString("REPORT_DAYS")); // 举报的天数
	public static final Integer CONSULT_DAYS =  Integer.parseInt(BUNDLE.getString("CONSULT_DAYS")); // 咨询的天数
	public static final Integer DELAY_DAYS =  Integer.parseInt(BUNDLE.getString("DELAY_DAYS")); // 延期的天数
	public static final Integer SORTCODE =  Integer.parseInt(BUNDLE.getString("SORTCODE")); // 2019年最大码（仅第一次上线修改，以后不用修改）
	public static final String ONLINETIME =  BUNDLE.getString("ONLINETIME"); // 2019年第一次上线时间

	
	public static String getResource(String key) {
		return BUNDLE.getString(key);
	}
}
