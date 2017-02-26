package com.tjrac.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjrac.dto.Exposer;
import com.tjrac.dto.SeckillExecution;
import com.tjrac.dto.SeckillResult;
import com.tjrac.entity.Seckill;
import com.tjrac.enums.SeckillStatEnum;
import com.tjrac.exception.RepeatKillException;
import com.tjrac.exception.SeckillCloseException;
import com.tjrac.exception.SeckillException;
import com.tjrac.service.SeckillService;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	@Resource
	private SeckillService seckillService;
	
	/**
	 * 秒杀列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model){
		List<Seckill> list = this.seckillService.findAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	/**
	 * 获取详情页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,  Model model){
		if(seckillId == null){
			return "redirect:/seckill/list";
		}
		Seckill seckill = this.seckillService.findById(seckillId);
		if(seckill == null){
			return "redirect:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	
	//ajax   返回 json
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, 
					produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SeckillResult<Exposer> result = null;
		try {
			Exposer exposer = this.seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SeckillResult(false, e.getMessage());
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
			       produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, 
					@PathVariable("md5") String md5, 
					@CookieValue(value="killphone", required = false) Long phone){
		
		if(phone == null){
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		
		
		try {
			SeckillExecution seckillExecution =	this.seckillService.executeSeckill(seckillId, md5, phone);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (SeckillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(false, execution);
		}
		
	}
	
	
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	public SeckillResult<Long> time(){
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
