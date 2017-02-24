package com.tjrac.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tjrac.entity.Seckill;

/**
 * ��ɱ�����dao����
 * @author FengXiang
 *
 */
public interface SeckillDao {
	/**
	 * �����
	 * @param seckillId  ָ����Ʒid
	 * @param killTime 
	 * @return
	 */
	int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);
	
	/**
	 * ����id��ѯ��Ʒ
	 * @param seckillId
	 * @return
	 */
	Seckill findById(Long seckillId);
	
	/**
	 * ��ѯ������ɱ  ��ҳ
	 * @param beginIndex
	 * @param max
	 * @return
	 */
	List<Seckill> findAll(@Param("beginIndex") int beginIndex, @Param("max") int max);
}
