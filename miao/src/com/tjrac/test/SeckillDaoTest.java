package com.tjrac.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjrac.dao.SeckillDao;
import com.tjrac.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

	@Autowired
	private SeckillDao seckillDao;
	

	@Test
	public void testFindById() {
		long id = 1000;
		Seckill seckill = this.seckillDao.findById(id);
		System.out.println(seckill);
	}

	@Test
	public void testFindAll() {
		List<Seckill> all = this.seckillDao.findAll(0, 4);
		for (Seckill seckill : all) {
			System.out.println(seckill);
		}
	}

	@Test
	public void testReduceNumber() {
		Date killtime = new Date();
		int number = this.seckillDao.reduceNumber(1000L, killtime);
		System.out.println(number);
	}
}
