package com.tjrac.exception;

/**
 * ��ɱ�ر��쳣
 * @author FengXiang
 *
 */
public class SeckillCloseException extends SeckillException{
	
	public SeckillCloseException(String message){
		super(message);
	}
	
	public SeckillCloseException(String message, Throwable cause){
		super(message);
	}
	
}
