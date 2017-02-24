package com.tjrac.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjrac.dao.SuccessKilledDao;
import com.tjrac.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

	@Autowired
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled() {
		Long seckillId = 1001L;
		Long userPhone = 123123123L;
		int killed = this.successKilledDao.insertSuccessKilled(seckillId , userPhone );
		System.out.println(killed);
	}

	@Test
	public void testFindByIdWithSeckill() {
		Long seckillId = 1001L;
		Long userPhone = 123123123L;
		SuccessKilled successKilled = this.successKilledDao.findByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
	}

}
