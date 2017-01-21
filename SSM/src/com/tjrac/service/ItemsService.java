package com.tjrac.service;

import java.util.List;

import com.tjrac.po.Items;

public interface ItemsService {

	/**
	 * ��ѯ������Ʒ
	 * @return
	 */
	public List<Items> findAll();

	/**
	 * ��ѯָ����Ʒ
	 * @param id
	 * @return
	 */
	public Items findById(Integer id);

	/**
	 * ������Ʒ
	 * @param items
	 */
	public void update(Items items);

	/**
	 * ɾ����Ʒ
	 * @param id
	 */
	public void deleteById(Integer id);

}
