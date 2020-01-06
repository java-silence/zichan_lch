/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showUserSelect(id, type, all) {
    var data = getUser(type);
    // console.log(data);
    var select = $("#" + id);
    select.empty();
    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
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
            url : '/users/listusers',  //?type=' + type,
            async : false,
            success : function(data) {
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

function getUserHeadImgUrl(type) {
    var v = {};
        $.ajax({
            type : 'get',
            url : '/users/listusers',  //?type=' + type,
            async : false,
            success : function(data) {
                $.each(data, function(i, d) {
                    v[d.id] = d.headImgUrl;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });

    return JSON.parse(sessionStorage[type]);
}

//显示某一部门下某一角色用户
function showUserbyDept(id, deptid, posid) {

    $.ajax({
        type : 'get',
        url : '/users/listbydept?deptid=' + deptid +'&posid=' + posid ,
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

//显示某一角色用户
function showUserbyRole(id, roleid) {

    $.ajax({
        type : 'get',
        url : '/users/getUserByRoleid?roleId=' + roleid ,
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
        url : '/users/getUsersByRoleDept?roleId=' + roleid ,
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

//获取当前登录用户信息
function getCurrentUser(){
    var user = null;
    $.ajax({
        type : 'get',
        url : '/users/current1',
        async : false,
        success : function(data) {
            user = data;
        }
    });
    return user;
}