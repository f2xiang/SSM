package com.tjrac.service;

import java.util.List;

import com.tjrac.dto.Exposer;
import com.tjrac.dto.SeckillExecution;
import com.tjrac.entity.Seckill;
import com.tjrac.exception.RepeatKillException;
import com.tjrac.exception.SeckillCloseException;
import com.tjrac.exception.SeckillException;

/**
 * ��ɱҵ��Service
 * @author FengXiang
 *
 */
public interface SeckillService {
	
	/**
	 * ��ѯ������ɱ��¼
	 * @return
	 */
	List<Seckill> findAll();
	
	
	/**
	 * ����id��ѯ��ɱ
	 * @param seckillId
	 * @return
	 */
	Seckill findById(Long seckillId);
	
	
	/**
	 * ��ɱ����ʱ�������ɱ�ӿڵĵ�ַ���������ϵͳʱ�����ɱʱ��
	 * @param seckillId
	 */
    Exposer exportSeckillUrl(Long seckillId);
    
    
    /**
     * ִ����ɱ����
     * @param seckillId
     * @param md5
     * @param userPhone
     */
    SeckillExecution executeSeckill(Long seckillId, String md5, Long userPhone)
    	throws SeckillException, RepeatKillException, SeckillCloseException;
    
    
}
