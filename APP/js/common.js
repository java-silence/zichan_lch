//form序列化为json
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");
	
	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
}

//格式化列表值
function liebiao(data){
	 var d = data.data;
	 for(var i=0;i<d.length;i++){
		  for(l in d[i]){
			  if(!d[i][l]){
				  d[i][l] = "";
			  }
		  }
	 }
}

//格式化对象
function formatData(data){
    for(l in data){
		if(data[l]==null){
			data[l] = "";
		}
        // if(!data[l]){
        //     data[l] = "";
        // }
    }
}

//重新渲染表单
function renderForm(){
    layui.use('form', function(){
        var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
        form.render();
    });
}

//验证数量是否为非负整数
function matchNum(num){
    var  type = "^[0-9]*[1-9][0-9]*$";
    var  re = new  RegExp(type);
    try {
        if(num.match(re)==null)
        {
            return false;
        }
    }catch (e) {

    }
    return true
}