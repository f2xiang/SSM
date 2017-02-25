package com.tjrac.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.tjrac.dao.SeckillDao;
import com.tjrac.dao.SuccessKilledDao;
import com.tjrac.dto.Exposer;
import com.tjrac.dto.SeckillExecution;
import com.tjrac.entity.Seckill;
import com.tjrac.entity.SuccessKilled;
import com.tjrac.enums.SeckillStatEnum;
import com.tjrac.exception.RepeatKillException;
import com.tjrac.exception.SeckillCloseException;
import com.tjrac.exception.SeckillException;
import com.tjrac.service.SeckillService;

@Service
@Transactional
public class SeckillServiceImpl implements SeckillService{

	@Resource
	private SeckillDao seckillDao;
	
	@Resource
	private SuccessKilledDao successKilledDao;
	
	//md5加密的时候加盐
	private final String slat = "d32deofp-fwpeifwofc";
	
	@Override
	public List<Seckill> findAll() {
		return this.seckillDao.findAll(0, 4);
	}

	@Override
	public Seckill findById(Long seckillId) {
		return this.seckillDao.findById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(Long seckillId) {
		Seckill seckill = this.seckillDao.findById(seckillId);
		if(seckill == null){
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		if(now.getTime() < startTime.getTime()    //秒杀还没开始
				|| now.getTime() > endTime.getTime()){  //秒杀已经结束
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	
	private String getMD5(Long seckillId){
		String base = seckillId + "/" + slat;   //id + 盐  转为 MD5
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	
	
	@Override
	public SeckillExecution executeSeckill(Long seckillId, String md5,
			Long userPhone) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if(md5 == null ||  !md5.equals(getMD5(seckillId))){
			throw new SeckillException("数据篡改");
		}
		//执行秒杀操作  --  减库存 -- 添加购买记录
		Date killTime = new Date();
		
		
		try {
			int updateNumber = this.seckillDao.reduceNumber(seckillId, killTime);
			if(updateNumber <= 0){
				//没有更新记录
				throw new SeckillCloseException("秒杀结束");
			}else{
				//记录购买行为 --唯一插入
				int insertCount = this.successKilledDao.insertSuccessKilled(seckillId, userPhone);
				if(insertCount <= 0){
					throw new RepeatKillException("重复秒杀");
				}else{
					//秒杀成功
					SuccessKilled successKilled = this.successKilledDao.findByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			//通用异常
			e.printStackTrace();
			throw new SeckillException("seckill error" + e.getMessage());
		}
	}

}
