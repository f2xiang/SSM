package com.tjrac.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tjrac.dto.Exposer;
import com.tjrac.dto.SeckillExecution;
import com.tjrac.entity.Seckill;
import com.tjrac.service.SeckillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
					   "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testFindAll() {
		List<Seckill> list = this.seckillService.findAll();
		
		for (Seckill seckill : list) {
			System.out.println(seckill);
		}
	}

	@Test
	public void testFindById() {
		Long seckillId = 1004L;
		Seckill seckill = this.seckillService.findById(seckillId);
		System.out.println(seckill);
	}

	@Test
	public void testExportSeckillUrl() {
		Long seckillId = 1004L;
		Exposer exposer = this.seckillService.exportSeckillUrl(seckillId );
		System.out.println(exposer);
	}

	@Test
	public void testExecuteSeckill() {
		Long userPhone = 123123124L;
		Long seckillId = 1004L;
		String md5 = "ad80bc9a8c243ac2d2eb9f44ebbdadbd";
		SeckillExecution execution = this.seckillService.executeSeckill(seckillId , md5 , userPhone );
		System.out.println(execution);
	}

}
