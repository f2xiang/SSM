package com.tjrac.exception;

/**
 * 重复秒杀异常--运行时异常
 * @author FengXiang
 *
 */
public class RepeatKillException extends SeckillException{
	
	public RepeatKillException(String message){
		super(message);
	}
	
	public RepeatKillException(String message, Throwable cause){
		super(message);
	}
	
}
