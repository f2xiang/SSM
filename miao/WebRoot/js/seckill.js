//存放交互逻辑主要代码
//模块化

var seckill = {
	//封装秒杀相关的ajax的url
	URL : {
		now : function(){
			return '/seckill/time/now';
		},
		exposer : function(seckillId){
			return '/seckill/'+seckillId+"/exposer";
		},
		exection : function(seckillId, md5){
			return '/' + seckillId + '/' + md5 + '/execution';
		}
	},
	validatePhone : function(phone){
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		} else{
			return false;
		}
	},
	//执行秒杀
	handlerKill : function(seckillId, node){
		node.hide().html("<button class='btn btn-primary btn-1g' id='killBtn'> 开始秒杀 <button/>");
		$.post(seckill.URL.exposer , {}, function(result){
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//开启秒杀
					//获取秒杀的地址
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.exection;
					$("#killBtn").one('click',function(){  //one:点击一次
						//执行秒杀请求操作
						$(this).addClass('disable');  //禁用按钮
						//发送秒杀请求
						$.post(killUrl, {}, function(result){
							if(result && result['success']){
								var killResult = result['data'];
								var state = result['state'];
								var stateInfo = result['stateInfo'];
								//显示秒杀的结果
								node.html("<span class='label label-success'>"+stateInfo+"</span>");
							}
						});
						node.show();
					});
				}else{
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					//时间有偏差 重新进行时间
					seckill.countdown(seckillId, now, start, end);
				}
			}
		});
	}
	//验证现在的时间
	countdown : function(seckillId, nowTime, startTime, endTime){
		var seckillbox = $("#seckill-box");
		if(nowTime > endTime){
			seckillbox.html("秒杀结束");
		}else if(nowTime < startTime){
			//秒杀没开始 计时操作
			var killtime = new Date(startTime + 1000);
			seckillbox.countdown(killtime, function(event){
				var format = event.strftime('秒杀计时： %D天 %H时 %M分 %S秒');
				seckillbox.html(format);
			}).on('finish.countdown', function(){
				//倒计时结束后的回调函数
				//获取秒杀接口
				
			});
		}else{
			//秒杀开始
			seckill.handlerKill(seckillId, node);
		}
	}
	detail : {
		//详情页初始化
		init : function(params){
			//手机验证和登录  ， 计时交互
			//规划交互流程
			var killPhone = $.cookie('killPhone');
			//验证手机号
			if(!seckill.validatePhone(killPhone)){
				var killPhoneModal = $("#killPhoneModal");
				killPhoneModal.modal({
					show : true,  //显示弹出层
					backdrop: 'static', //禁止位置关闭
					keyboard : false  //关闭键盘事件
				});
				$("#killPhoneBtn").click(function(){
					var inputPhone = $("#killPhoneKey").val();
					if(seckill.validatePhone(inputPhone)){
						//电话写入cookie
						$.cookie("killPhone",inputPhone, { expires:10, path:"/seckill"});
						//刷新页面
						window.location.reload();
					} else{
						$("#killPhoneMessage").hide().html("<label class='label label-danger'>the number is wrong!!</label>").show(300);
					}
				});
			}
			
			//成功登陆 --开始计时交互
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			$.get(seckill.URL.now() ,{},function(result){
				if(result && result['success']){
					var nowtime = result['data'];
					countdown(seckillId, nowtime, startTime, endTime);
				}else{
					
				}
			});
			
			
		}
	}
}