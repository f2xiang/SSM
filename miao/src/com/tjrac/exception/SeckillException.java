package com.tjrac.exception;

/**
 * 所有秒杀相关的通用异常
 * @author FengXiang
 *
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}
	
	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}


	
}
