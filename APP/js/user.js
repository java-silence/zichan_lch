/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showUserSelect(id, type, all) {
    var data = getUser(type);
    // console.log(data);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>全部</option>");
    }

    $.each(data, function(id, nickname) {
        select.append("<option value ='" + id + "'>" + nickname + "</option>");
    });

    return data;
}

function getUser(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/users/listusers',  //?type=' + type,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data) {
                // console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.nickname;
                });

                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function showUserbyRole(id, roleid) {

    $.ajax({
        type : 'get',
        url : server_url+'/users/getUserByRoleid?roleId=' + roleid ,
        headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
        async : false,
        success : function(data) {
            var select = $("#" + id);
            select.append("<option value=''>请选择</option>");
            $.each(data, function(i, d) {
                select.append("<option value ='" + d.id + "'>" + d.nickname + "</option>");
            });
            return data;
        }
    });

}


//显示登录用户所在部门，某一角色用户
function showDeptUserbyRole(id, roleid) {

    $.ajax({
        type : 'get',
        url : server_url+'/users/getUsersByRoleDept?roleId=' + roleid ,
        headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
        async : false,
        success : function(data) {
            var select = $("#" + id);
            select.append("<option value=''>请选择</option>");
            $.each(data, function(i, d) {
                select.append("<option value ='" + d.id + "'>" + d.nickname + "</option>");
            });
            return data;
        }
    });
}

//显示登录用户所在部门，某一角色用户
function userByCondition(data) {  //data {}
    var userList = []
    $.ajax({
        type : 'get',
        url : server_url+'/users/listByCondition',
        headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
		data:data,
        async : false,
        success : function(data) {
               userList = data;
        }
    });
	return userList;
}