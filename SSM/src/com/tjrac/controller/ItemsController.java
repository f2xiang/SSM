package com.tjrac.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjrac.po.Items;
import com.tjrac.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	
	@Resource
	private ItemsService itemsService;
	
	
	//查询所有商品
	@RequestMapping("findAll")
	public String findAll(Model model){
		List<Items> itemsList = this.itemsService.findAll();
		
		model.addAttribute("itemsList", itemsList);
		
		return "itemsList";
	}
	
	//跳转到修改页面
	@RequestMapping("edit")
	public String edit(Integer id, Model model){
		//根据id查询商品
		Items items = this.itemsService.findById(id);
		
		model.addAttribute("item", items);
		
		return "editItem";
	}
	
	//更新到数据库
	@RequestMapping("update")
	public String update(Items items){
		this.itemsService.update(items);
		
		return "redirect:findAll.do";
	}
	
	
	
	//删除
	@RequestMapping("delete")
	public String delete(Integer id){
		this.itemsService.deleteById(id);
		
		return "redirect:findAll.do";
	}
	
	//批量删除
	@RequestMapping("deleteByIds")
	public String deleteByIds(Integer [] id){
		
		for (Integer i : id) {
			this.itemsService.deleteById(i);
		}
		return "redirect:findAll.do";
	}
}
