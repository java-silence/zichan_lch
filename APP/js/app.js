/**
 * 演示程序当前的 “注册/登录” 等操作，是基于 “本地存储” 完成的
 * 当您要参考这个演示程序进行相关 app 的开发时，
 * 请注意将相关方法调整成 “基于服务端Service” 的实现。
 **/
(function($, owner) {
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.username = loginInfo.username || '';
		loginInfo.password = loginInfo.password || '';
		if (loginInfo.username.length < 3) {
			return callback('账号最短为 3 个字符');
		}
		if (loginInfo.password.length < 5) {
			return callback('密码最短为 5 个字符');
		}
		
	
		
//		mui.post('http://192.168.1.107:8088/login',{
//				username:loginInfo.username,
//				password:loginInfo.password
//			},function(data){
//				alert('ajax' );
//				
//				//服务器返回响应，根据响应结果，分析是否登录成功；
//				if(data.token == undefined){
//					
//					alert(data.message);
//					return callback(data.message);
//				}else{
//					alert(data.token);
//					return owner.createState(loginInfo.username, data.token,callback);
//				}
//			},'json'
//		);
      
		mui.ajax(server_url+'/login',{
			data:{
				username:loginInfo.username,
				password:loginInfo.password
			},
			dataType:'json',//服务器返回json格式数据
			type:'post',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；
			//headers:{'Content-Type':'application/json'},	              
			success:function(data){
				console.log(JSON.stringify(data));
				//获取设备信息
		        var clientInfo = plus.push.getClientInfo();
		        var clientid = clientInfo.clientid;
		        //console.log(clientid);
		        
		        owner.saveClientInfo(clientid,data.token);
						owner.getLoginUser(data.token,callback);
		        
			},
			error:function(xhr,type,errorThrown){
				//异常处理；

				var msg = xhr.responseText;
				console.log("异常"  + msg);
				var response = JSON.parse(msg);
				alert(response.message);
				return callback(response.message);
			}
		});

	};
	
	owner.saveClientInfo = function(clientid,token) {
		mui.ajax(server_url+'/users/saveClientInfo',{
			data:{
				clientid:clientid
			},
			dataType:'json',//服务器返回json格式数据
			type:'get',//HTTP请求类型
			timeout:10000,//超时时间设置为10秒；    
			headers:{'token':token},
			success:function(data){

			},
			error:function(xhr,type,errorThrown){
				//异常处理；
				var msg = xhr.responseText;
				var response = JSON.parse(msg);
				alert(response.message);
				return callback(response.message);
			}
		});
	};
	
	owner.getLoginUser = function(token,callback) {
			mui.ajax(server_url+'/users/current1',{
				dataType:'json',//服务器返回json格式数据
				type:'get',//HTTP请求类型
				timeout:10000,//超时时间设置为10秒；    
				headers:{'token':token},
				success:function(data){
	          // console.log(JSON.stringify(data))
						//服务器返回响应，根据响应结果，分析是否登录成功；
						owner.createState(data.username, token,callback,data);
				},
				error:function(xhr,type,errorThrown){
					//异常处理；
					var msg = xhr.responseText;
					var response = JSON.parse(msg);
					alert(response.message);
					return callback(response.message);
				}
			});
		};

	owner.createState = function(name, mytoken,callback,user) {
		var state = owner.getState();
		state.account = name;
		state.token = mytoken;
		state.user = user;
		owner.setState(state);
		return callback();
	};

	

	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};

	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		localStorage.setItem('$state', JSON.stringify(state));
		//var settings = owner.getSettings();
		//settings.gestures = '';
		//owner.setSettings(settings);
	};

	var checkEmail = function(email) {
		email = email || '';
		return (email.length > 3 && email.indexOf('@') > -1);
	};

	/**
	 * 找回密码
	 **/
	owner.forgetPassword = function(email, callback) {
		callback = callback || $.noop;
		if (!checkEmail(email)) {
			return callback('邮箱地址不合法');
		}
		return callback(null, '新的随机密码已经发送到您的邮箱，请查收邮件。');
	};

	/**
	 * 获取应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
	}

	/**
	 * 设置应用本地配置
	 **/
	owner.getSettings = function() {
			var settingsText = localStorage.getItem('$settings') || "{}";
			return JSON.parse(settingsText);
		}
		/**
		 * 获取本地是否安装客户端
		 **/
	owner.isInstalled = function(id) {
		if (id === 'qihoo' && mui.os.plus) {
			return true;
		}
		if (mui.os.android) {
			var main = plus.android.runtimeMainActivity();
			var packageManager = main.getPackageManager();
			var PackageManager = plus.android.importClass(packageManager)
			var packageName = {
				"qq": "com.tencent.mobileqq",
				"weixin": "com.tencent.mm",
				"sinaweibo": "com.sina.weibo"
			}
			try {
				return packageManager.getPackageInfo(packageName[id], PackageManager.GET_ACTIVITIES);
			} catch (e) {}
		} else {
			switch (id) {
				case "qq":
					var TencentOAuth = plus.ios.import("TencentOAuth");
					return TencentOAuth.iphoneQQInstalled();
				case "weixin":
					var WXApi = plus.ios.import("WXApi");
					return WXApi.isWXAppInstalled()
				case "sinaweibo":
					var SinaAPI = plus.ios.import("WeiboSDK");
					return SinaAPI.isWeiboAppInstalled()
				default:
					break;
			}
		}
	}
}(mui, window.app = {}));