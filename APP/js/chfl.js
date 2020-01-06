/**
 * Created by fanlinglong on 2019/3/24.
 */

function showChflSelect(id, type, all) {

    sessionStorage.clear();

    var data = {};

    if (type == "chfl"){
		
        data = getChfl(type);
    }

    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>全部分类</option>");
    }
    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    

    return data;
}

function getChfl(type) {

    var v = sessionStorage[type];

    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/chfls/listchfls?type=' + type,
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data){
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.cname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}