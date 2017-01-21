package com.tjrac.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjrac.dao.ItemsMapper;
import com.tjrac.po.Items;
import com.tjrac.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService{

	@Resource
	private ItemsMapper itemsMapper;
	
	@Override
	public List<Items> findAll() {
		return this.itemsMapper.findAll();
	}

	@Override
	public Items findById(Integer id) {
		return this.itemsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Items items) {
		this.itemsMapper.updateByPrimaryKey(items);
	}

	@Override
	public void deleteById(Integer id) {
		this.itemsMapper.deleteByPrimaryKey(id);
	}

}
