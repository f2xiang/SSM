<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>秒杀详情页</title>
      <%@ include file="common/head.jsp" %>
   </head>
   <body>
 	  <div id="container">
 	  	<div class="panel panel-default text-center">
 	  		<div class="panel-heading"><h1><br> </h1><h1>${seckill.name } </h1> </div>
 	  		<div class="panel-body">
 	  			
 	  		</div>
 	  		
 	  	</div>
 	  </div>	
	  <div id="killPhoneModal" class="modal fade">
	      <div class="modal-dialog">
		  	 <div class="modal-content">
		  	 	<div class="modal-header">
		  	 		<h3 class="modal-title text-center">
		  	 			<span class="glyphicon glyphicon-phone"></span>秒杀电话：
		  	 		</h3>
		  	 	</div>
		  	 	<div class="modal-body">
		  	 		<div class="row">
		  	 			<div class="col-xs-8 col-xs-offset-2">
		  	 				<input type="text" name="killPhone" id="killPhoneKey"
		  	 				placeholder="请输入手机号0.0" class="form-control"> 
		  	 			</div>
		  	 		</div>
		  	 	</div>
		  	 	<div class="modal-footer">
		  	 		<span id="killPhoneMessage" class=""></span>
		  	 		<button type="button" id="killPhoneBtn" class="btn btn-success">
		  	 			<span class="glyphicon glyphicon-phone"></span>
		  	 			Submit
		  	 		</button>
		  	 	</div>
		  	 </div>
	  	  </div>
	  </div>
   </body>
   
   <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
   <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
   <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
   <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
   <script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js" type="text/javascript"></script>
   <script src="http://cdn.bootcss.com/jquery-countdown/2.0.2/jquery.countdown.min.js" type="text/javascript"></script>

   <!-- 开始编写交互逻辑 -->
   <script type="text/javascript" src="../js/seckill.js"></script>
   <script type="text/javascript">
   		$(function(){
   			seckill.detail.init({
   				seckillId : ${seckill.seckillId },
   				startTime : ${seckill.startTime.time},
   				endTime : ${seckill.endTime.time}
   			});
   		});
   </script>
</html>
