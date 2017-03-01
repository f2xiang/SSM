//��Ž����߼���Ҫ����
//ģ�黯

var seckill = {
	//��װ��ɱ��ص�ajax��url
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
	//ִ����ɱ
	handlerKill : function(seckillId, node){
		node.hide().html("<button class='btn btn-primary btn-1g' id='killBtn'> ��ʼ��ɱ <button/>");
		$.post(seckill.URL.exposer , {}, function(result){
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//������ɱ
					//��ȡ��ɱ�ĵ�ַ
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.exection;
					$("#killBtn").one('click',function(){  //one:���һ��
						//ִ����ɱ�������
						$(this).addClass('disable');  //���ð�ť
						//������ɱ����
						$.post(killUrl, {}, function(result){
							if(result && result['success']){
								var killResult = result['data'];
								var state = result['state'];
								var stateInfo = result['stateInfo'];
								//��ʾ��ɱ�Ľ��
								node.html("<span class='label label-success'>"+stateInfo+"</span>");
							}
						});
						node.show();
					});
				}else{
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					//ʱ����ƫ�� ���½���ʱ��
					seckill.countdown(seckillId, now, start, end);
				}
			}
		});
	}
	//��֤���ڵ�ʱ��
	countdown : function(seckillId, nowTime, startTime, endTime){
		var seckillbox = $("#seckill-box");
		if(nowTime > endTime){
			seckillbox.html("��ɱ����");
		}else if(nowTime < startTime){
			//��ɱû��ʼ ��ʱ����
			var killtime = new Date(startTime + 1000);
			seckillbox.countdown(killtime, function(event){
				var format = event.strftime('��ɱ��ʱ�� %D�� %Hʱ %M�� %S��');
				seckillbox.html(format);
			}).on('finish.countdown', function(){
				//����ʱ������Ļص�����
				//��ȡ��ɱ�ӿ�
				
			});
		}else{
			//��ɱ��ʼ
			seckill.handlerKill(seckillId, node);
		}
	}
	detail : {
		//����ҳ��ʼ��
		init : function(params){
			//�ֻ���֤�͵�¼  �� ��ʱ����
			//�滮��������
			var killPhone = $.cookie('killPhone');
			//��֤�ֻ���
			if(!seckill.validatePhone(killPhone)){
				var killPhoneModal = $("#killPhoneModal");
				killPhoneModal.modal({
					show : true,  //��ʾ������
					backdrop: 'static', //��ֹλ�ùر�
					keyboard : false  //�رռ����¼�
				});
				$("#killPhoneBtn").click(function(){
					var inputPhone = $("#killPhoneKey").val();
					if(seckill.validatePhone(inputPhone)){
						//�绰д��cookie
						$.cookie("killPhone",inputPhone, { expires:10, path:"/seckill"});
						//ˢ��ҳ��
						window.location.reload();
					} else{
						$("#killPhoneMessage").hide().html("<label class='label label-danger'>the number is wrong!!</label>").show(300);
					}
				});
			}
			
			//�ɹ���½ --��ʼ��ʱ����
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