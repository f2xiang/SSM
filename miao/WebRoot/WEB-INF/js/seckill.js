//��Ž����߼���Ҫ����
//ģ�黯

var seckill = {
	//��װ��ɱ��ص�ajax��url
	URL : {
		
	},
	validatePhone : function(phone){
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		} else{
			return false;
		}
	},
	detail : {
		//����ҳ��ʼ��
		init : function(params){
			//�ֻ���֤�͵�¼  �� ��ʱ����
			//�滮��������
			var killPhone = $.cookie('killPhone');
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			//��֤�ֻ���
			if(! seckill.validatePhone(killPhone)){
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
						$.cookie('killPhone',inputPhone, {expires:7,path:'/seckill'});
						//ˢ��ҳ��
						window.location.reload();
					} else{
						$("#killPhoneMessage").hide().html("<label class='label label-danger'>�ֻ��������</label>").show(300);
					}
				});
			}
		}
	}
}