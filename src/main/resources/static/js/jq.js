$.ajaxSetup({
	cache : false,
	headers : {
		"token" : localStorage.getItem("token")
	},
	error : function(xhr, textStatus, errorThrown) {
		var msg = xhr.responseText;
		var response = JSON.parse(msg);
		var code = response.code;
		var message = response.message;
		if (code == 400) {
			layer.msg(message);
		} else if (code == 401) {
			localStorage.removeItem("token");
			location.href = '/login.html';
		} else if (code == 403) {
			console.log("未授权:" + message);
			layer.msg('未授权');
		} else if (code == 500) {
			layer.msg('系统错误：' + message);
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

function buttonDelItem(data, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<button class='layui-btn layui-btn-xs' title='删除' onclick='delItem(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btn.prop("outerHTML");
}

function buttonDels(data, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btns = $("<button class='layui-btn layui-btn-xs' title='删除' onclick='dels(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
	return btns.prop("outerHTML");
}

function buttonAudit(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }
    var btn = $("<button class='layui-btn layui-btn-xs' title='审核' onclick='audit(\"" + data +"\")'><i class='layui-icon'>&#xe605;</i></button>");
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


function buttonView(href, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<button class='layui-btn layui-btn-xs' title='查看' onclick='OpenView(\"" + href +"\")'><i class='layui-icon'>&#xe63c;</i></button>");
	return btn.prop("outerHTML");
}
function OpenView(href,title){
	layer.open({
		type: 2
		,title: title
		,content: href
		,maxmin: true,
		//,area: ['550px', '550px']
		area:  ['80%','80%']
	});

}
function buttonStartProcess(data, permission, pers){
	if(permission != ""){
		if ($.inArray(permission, pers) < 0) {
			return "";
		}
	}

	var btn = $("<button class='layui-btn layui-btn-xs' title='提交' onclick='startProcess(\""+ data +"\")'><i class='layui-icon'>&#xe62f;</i></button>");
	return btn.prop("outerHTML");
}

// 报废子项再次提交
function buttonStartItemProcess(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }

    var btn = $("<button class='layui-btn layui-btn-xs' title='提交' onclick='startItemProcess(\""+ data +"\")'><i class='layui-icon'>&#xe62f;</i></button>");
    return btn.prop("outerHTML");
}

// 下载
function buttonDown(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }
    var btn = $("<button class='layui-btn layui-btn-xs' title='下载' onclick='download(\""+ data +"\")'><i class='layui-icon'>&#xe601;</i></button>");
    return btn.prop("outerHTML");
}

// 财务确认正常,审核恢复
function huifuItemProcess(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }

    var btn = $("<button class='layui-btn layui-btn-xs' title='恢复正常' onclick='huifuZcInfo(\""+ data +"\")'><i class='layui-icon'>&#xe669;</i></button>");
    return btn.prop("outerHTML");
}

function buttonAdd(hrefs, permission, pers){
		if(permission != ""){
			if ($.inArray(permission, pers) < 0) {
				return "";
			}
		}

		var btn = $("<button class='layui-btn layui-btn-xs' title='添加成员' onclick='window.location=\"" + hrefs +"\"'><i class='layui-icon'>&#xe608;</i>添加成员</button>");
		return btn.prop("outerHTML");
}

function deleteCurrentTab(){
	var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
	parent.active.tabDelete(lay_id);
}

function btnNoAction(data, permission, pers){
    if(permission != ""){
        if ($.inArray(permission, pers) < 0) {
            return "";
        }
    }

    var btn = $("<button class='layui-btn layui-btn-xs' title='已提交,暂无操作'><i class='layui-icon'>&#x1005;</i></button>");
    return btn.prop("outerHTML");
}

$(document).on('keydown','.layui-table-edit',function(e){
	var td = $(this).parent('td'),
		tr = td.parent('tr'),
		trs = tr.parent().parent().find("tr"),
		tr_index = tr.index(),
		td_index = td.index(),
		td_last_index = tr.find('[data-edit="text"]:last').index(),
		td_first_index = tr.find('[data-edit="text"]:first').index();
	switch(e.keyCode){
		case 13:
		case 39:
		    td.nextAll('[data-edit="text"]:first').click();
		    if (td_index == td_last_index){
		    	tr.next().find("td").eq(td_first_index).click();
		    	if (tr_index == trs.length - 1)
		    		trs.eq(0).find("td").eq(td_first_index).click;
			}
			setTimeout(function(){$('.layui-table-edit').select()},0);
		    break;
		case 37:
            td.prevAll('[data-edit="text"]:first').click();
            setTimeout(function(){$('.layui-table-edit').select()},0);
            break;
        case 38:
            td.prev().find('td').eq(td_index).click();
            setTimeout(function(){$('.layui-table-edit').select()},0);
            break;
        case 40:
            td.next().find('td').eq(td_index).click();
            setTimeout(function(){$('.layui-table-edit').select()},0);
            break;
	}
})
