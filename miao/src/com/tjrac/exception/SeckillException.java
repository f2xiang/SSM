package com.tjrac.exception;

/**
 * ������ɱ��ص�ͨ���쳣
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
