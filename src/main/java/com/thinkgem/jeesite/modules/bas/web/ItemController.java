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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.ResponseResult;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.bas.entity.Item;
import com.thinkgem.jeesite.modules.bas.service.ItemService;

/**
 * 
 * 
 * @ClassName ItemController
 * @Description 指标项管理controller
 * @author lihongtao
 * @date 2019年1月29日
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/bas/item")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;

	/**
	 * 
	 * 
	 * @Title get
	 * @author lht 
	 * @date 2019年1月24日 
	 * @Description 根据id查询指标项
	 * @param id
	 * @return Item
	 * @throws
	 */
	@ModelAttribute
	public Item get(@RequestParam(required = false) String id) {
		Item entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = itemService.get(id);
		}
		if (entity == null) {
			entity = new Item();
		}
		return entity;
	}

	/**
	 * 
	 * 
	 * @Title list
	 * @author lht 
	 * @date 2019年1月24日 
	 * @Description 查询指标项列表(分页)
	 * @param item , request , response , model
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("bas:item:view")
	@RequestMapping(value = { "list", "" })
	public String list(Item item, HttpServletRequest request, HttpServletResponse response, Model model) {
		item.setParent(new Item("0"));
		Page<Item> page = itemService.findPage(new Page<Item>(request, response), item);
		model.addAttribute("page", page);
		return "modules/bas/itemList";
	}

	/**
	 * 
		 * 
		 * @Title form
		 * @author lht
		 * @date 2019年1月24日
		 * @Description 查询指标项及其子级(用于新增和编辑)
		 * @param item , model
		 * @return String
		 * @throws
	 */
	@RequiresPermissions("bas:item:view")
	@RequestMapping(value = "form")
	public String form(Item item, Model model) {
		if (!item.getIsNewRecord()) {
			item.setList(itemService.findListByParentId(item.getId()));
		}
		model.addAttribute("item", item);
		return "modules/bas/itemForm";
	}

	/**
	 * 
	 * 
	 * @Title save
	 * @author lht 
	 * @date 2019年1月24日 
	 * @Description 保存指标项
	 * @param item , model , redirectAttributes
	 * @return ResponseResult
	 * @throws
	 */
	@ResponseBody
	@RequiresPermissions("bas:item:edit")
	@RequestMapping(value = "save")
	public ResponseResult save(Item item, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, item)) {
			return ResponseResult.failure("表单验证失败");
		}
		itemService.save(item);
		addMessage(redirectAttributes, "保存指标项成功");
		return ResponseResult.success();
	}

	/**
	 * 
	 * 
	 * @Title delete
	 * @author lht 
	 * @date 2019年1月24日 
	 * @Description 删除指标项
	 * @param item , redirectAttributes
	 * @return String
	 * @throws
	 */
	@RequiresPermissions("bas:item:edit")
	@RequestMapping(value = "delete")
	public String delete(Item item, RedirectAttributes redirectAttributes) {
		itemService.delete(item);
		addMessage(redirectAttributes, "删除指标项成功");
		return "redirect:" + Global.getAdminPath() + "/bas/item/?repage";
	}

	/**
	 * 
		 * 
		 * @Title form
		 * @author lht
		 * @date 2019年1月24日
		 * @Description 查询指标项及其子级(用于查看)
		 * @param item , model
		 * @return String
		 * @throws
	 */
	@RequiresPermissions("bas:item:view")
	@RequestMapping(value = "view")
	public String view(Item item, Model model) {
		if (!item.getIsNewRecord()) {
			item.setList(itemService.findListByParentId(item.getId()));
		}
		model.addAttribute("item", item);
		return "modules/bas/itemView";
	}

}