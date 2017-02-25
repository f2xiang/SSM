package com.tjrac.service;

import java.util.List;

import com.tjrac.dto.Exposer;
import com.tjrac.dto.SeckillExecution;
import com.tjrac.entity.Seckill;
import com.tjrac.exception.RepeatKillException;
import com.tjrac.exception.SeckillCloseException;
import com.tjrac.exception.SeckillException;

/**
 * 秒杀业务Service
 * @author FengXiang
 *
 */
public interface SeckillService {
	
	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<Seckill> findAll();
	
	
	/**
	 * 根据id查询秒杀
	 * @param seckillId
	 * @return
	 */
	Seckill findById(Long seckillId);
	
	
	/**
	 * 秒杀开启时，输出秒杀接口的地址。否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
    Exposer exportSeckillUrl(Long seckillId);
    
    
    /**
     * 执行秒杀操作
     * @param seckillId
     * @param md5
     * @param userPhone
     */
    SeckillExecution executeSeckill(Long seckillId, String md5, Long userPhone)
    	throws SeckillException, RepeatKillException, SeckillCloseException;
    
    
}
