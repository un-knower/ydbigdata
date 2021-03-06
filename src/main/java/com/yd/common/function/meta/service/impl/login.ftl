<!DOCTYPE html>
<html>
<head>
<title>用户登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="ui/js/jquery-easyui/themes/orange/easyui.css"/>
<link rel="stylesheet" type="text/css" href="ui/js/jquery-easyui/themes/icon.css"/>
<script type="text/javascript" src="ui/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="ui/js/jquery-easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="ui/css/login.css"/>
<script type="text/javascript" src="ui/js/MD5+BASE64Encoder.js" ></script>
<script type="text/javascript">
	var urlPassCode = "/${cip_war_name}/public/common/user/getPassCode.do?time=";
	var urlLogin = "/${cip_war_name}/public/common/user/login.do";
	var urlIndex = "/${cip_war_name}/ui/view/index.html?actionId=index";
	localStorage.setItem('appId',"/${cip_war_name}");
	
	if (window != top)
		top.location.href = location.href;
	//为回车事件绑定自动登录业务处理
	$(function() {
		$("#imgCode").attr("src", urlPassCode+new Date());
		$(window).keydown(function(event) {
			if (event.keyCode == 13) {
				login();
			}
		});
	});

	/**
	 * 验证登陆表单，并提交登陆数据
	 */
	function login() {
		$("#userErrorMsg").html("");
		$("#passwordErrorMsg").html("");
		$("#codeErrorMsg").html("");
		$("#errorMsg").html("");
		if ("" == $.trim($("#userId").val())){
			$("#userErrorMsg").html("用户名不能为空");
			return false;
		}
		if("" == $.trim($("#password").val())){
			$("#passwordErrorMsg").html("密码不能为空");
			return false;
		}
		if(""==$.trim($("#passCode").val())) {
			$("#codeErrorMsg").html("验证码不能为空");
			return false;
		}
		var userId = $("#userId").val();
		var passCode = $("#passCode").val();
		var password = b64_md5($("#password").val());
		var oParameters = {
				"user_id" : userId,
				"pass_code" : passCode,
				"user_pwd" : password
		};
		
		$.ajax({
			url:urlLogin,
			data : JSON.stringify(oParameters),
			type : 'POST',
			dataType:"json",
		    contentType: "application/json",
			success : function(oData) {
				var errorCode = oData.errorCode;
				var msg = oData.msg;
				if(errorCode!=0){
					$("#errorMsg").html(errorCode + ":" + msg);
				}else {
					window.location.href = urlIndex;
					localStorage.setItem("userData",JSON.stringify(oData.data));
				}							
			},
		});
	}
	
	function fnChangePassCode(){
		//var randomnum=Math.random();
		//var date = new Date()
		$("#imgCode").attr('src',urlPassCode+new Date());
	}
</script>
</head>
<body>
	<div id="top">
		<div class="header">
			<div class="logo">
				<img src="ui/img/logo_login.jpg" />
			</div>
			<div class="title">${cip_app_name}</div>
		</div>
	</div>
	<form id="loginForm"
			action="#" method="post">
			<div id="main">
		<div class="main_top">
			<span class="ico"><img
				src="ui/img/ico.png" /></span> <span
				class="tit_wz">请输入您的用户名和密码及验证码进行登录</span>
		</div>
		<div class="list" >
			<span class="list_l">用户名：</span> <span class="list_r"><input
				name="userId" id="userId" type="text"
				data-options="required:true" />
			</span>
			<span id="userErrorMsg" style="color:red; height: 40px;line-height: 40px;"></span>
		</div>
		<div class="list">
			<span class="list_l">密码：</span> <span class="list_r"><input
				id="password" name="password" type="password" autocomplete="off" data-options="required:true" />
				</span>
			<span id="passwordErrorMsg" style="color:red; height: 40px;line-height: 40px;"></span>
		</div>
		<div class="list">
			<span class="list_l">验证码：</span> <span class="list_r2"><input
				id="passCode" name="passCode"  data-options="required:true"  />
				<img id="imgCode" alt="点击刷新" style="cursor: pointer; vertical-align: -5px; " onclick="fnChangePassCode()"/>	
			</span>
			<span id="codeErrorMsg" style="color:red; height: 40px;line-height: 40px;"></span>
		</div>
		<div style="height:10px; margin-top:10px; color: red;text-align: center;">
			<span id="errorMsg"></span>
		</div>
		<div class="list" align="center">
			<span class="list_ico"><input type="button" style="cursor: pointer;" onclick="login()" /></span>
		</div>
	</div>
	</form>
	
	<div class="shadow">
		<div class="footer">&copy;&nbsp;${cip_year}&nbsp;${cip_soft_company}&nbsp;&nbsp;版本：${cip_version}</div>
	</div>

</body>
</html>
