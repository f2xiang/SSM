package com.tjrac.dao;

import org.apache.ibatis.annotations.Param;

import com.tjrac.entity.SuccessKilled;

/**
 * ��ɱ�ɹ�Dao
 * @author FengXiang
 *
 */
public interface SuccessKilledDao {
	
	/**
	 * ������ɱ�ɹ���ϸ�� �ɹ����ظ�
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
	
	/**
	 * ��ѯSuccessKilled ��Я�� seckill����
	 * @param seckillId
	 * @return
	 */
	SuccessKilled findByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
