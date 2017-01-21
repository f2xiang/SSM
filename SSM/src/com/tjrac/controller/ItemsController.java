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
	
	
	//��ѯ������Ʒ
	@RequestMapping("findAll")
	public String findAll(Model model){
		List<Items> itemsList = this.itemsService.findAll();
		
		model.addAttribute("itemsList", itemsList);
		
		return "itemsList";
	}
	
	//��ת���޸�ҳ��
	@RequestMapping("edit")
	public String edit(Integer id, Model model){
		//����id��ѯ��Ʒ
		Items items = this.itemsService.findById(id);
		
		model.addAttribute("item", items);
		
		return "editItem";
	}
	
	//���µ����ݿ�
	@RequestMapping("update")
	public String update(Items items){
		this.itemsService.update(items);
		
		return "redirect:findAll.do";
	}
	
	
	
	//ɾ��
	@RequestMapping("delete")
	public String delete(Integer id){
		this.itemsService.deleteById(id);
		
		return "redirect:findAll.do";
	}
	
	//����ɾ��
	@RequestMapping("deleteByIds")
	public String deleteByIds(Integer [] id){
		
		for (Integer i : id) {
			this.itemsService.deleteById(i);
		}
		return "redirect:findAll.do";
	}
}
