package com.tjrac.dao;

import org.apache.ibatis.annotations.Param;

import com.tjrac.entity.SuccessKilled;

/**
 * 秒杀成功Dao
 * @author FengXiang
 *
 */
public interface SuccessKilledDao {
	
	/**
	 * 插入秒杀成功明细， 可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
	
	/**
	 * 查询SuccessKilled 并携带 seckill对象
	 * @param seckillId
	 * @return
	 */
	SuccessKilled findByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
