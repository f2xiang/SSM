package com.tjrac.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tjrac.entity.Seckill;

/**
 * 秒杀库存表的dao操作
 * @author FengXiang
 *
 */
public interface SeckillDao {
	/**
	 * 减库存
	 * @param seckillId  指定商品id
	 * @param killTime 
	 * @return
	 */
	int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);
	
	/**
	 * 根据id查询商品
	 * @param seckillId
	 * @return
	 */
	Seckill findById(Long seckillId);
	
	/**
	 * 查询所有秒杀  分页
	 * @param beginIndex
	 * @param max
	 * @return
	 */
	List<Seckill> findAll(@Param("beginIndex") int beginIndex, @Param("max") int max);
}
