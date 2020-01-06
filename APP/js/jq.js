$.ajaxSetup({
	cache : false,
	headers : {
		"token" : JSON.parse(localStorage.getItem('$state')).token
	},
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		if (code == 400) {
			mui.toast(message);
		} else if (code == 401) {
			localStorage.removeItem("token");
			location.href = '/login.html';
		} else if (code == 403) {
			console.log("未授权:" + message);
			mui.toast('未授权');
		} else if (code == 500) {
			mui.toast('系统错误：' + message);
		}
	}
});

function buttonDel(data, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}
	
	var btn = $("<button class='layui-btn layui-btn-xs' title='删除' onclick='del(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}

function buttonAudit(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }
    var btn = $("<button class='layui-btn layui-btn-xs' title='审核' onclick='audit(\"" + data +"\")'><i class='layui-icon'>&#xe618;</i></button>");
    return btn.prop("outerHTML");
}

function buttonUnAudit(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }

    var btn = $("<button class='layui-btn layui-btn-xs' title='弃审' onclick='unaudit(\"" + data +"\")'><i class='layui-icon'>&#xe65c;</i></button>");
    return btn.prop("outerHTML");
}

function buttonEdit(href, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}
	
	var btn = $("<button class='layui-btn layui-btn-xs' title='编辑' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe642;</i></button>");
	return btn.prop("outerHTML");
}


function deleteCurrentTab(){
	var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
	parent.active.tabDelete(lay_id);
}
