package com.tjrac.dto;

import com.tjrac.entity.SuccessKilled;
import com.tjrac.enums.SeckillStatEnum;

/**
 * 封装秒杀后的执行结果 
 * @author FengXiang
 *
 */
public class SeckillExecution {
	
	private Long seckillId;
	
	private Integer  state;  //状态
	
	private String stateInfo; //状态表示
	
	private SuccessKilled successKilled;  //秒杀成功对象
	
	public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStatEnum.getState();
		this.stateInfo = seckillStatEnum.getStateInfo();
	}

	
	public SeckillExecution(Long seckillId, SeckillStatEnum seckillStatEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStatEnum.getState();
		this.stateInfo = seckillStatEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}


	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}
	
	
}
