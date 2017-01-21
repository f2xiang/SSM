package com.tjrac.service;

import java.util.List;

import com.tjrac.po.Items;

public interface ItemsService {

	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Items> findAll();

	/**
	 * 查询指定商品
	 * @param id
	 * @return
	 */
	public Items findById(Integer id);

	/**
	 * 更新商品
	 * @param items
	 */
	public void update(Items items);

	/**
	 * 删除商品
	 * @param id
	 */
	public void deleteById(Integer id);

}
