/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showDeptSelect(id, type, all) {
    var data = getDept(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, deptname) {
        select.append("<option value ='" + id + "'>" + deptname + "</option>");
    });

    return data;
}

function getDept(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/depts/listdepts?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                //console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.deptname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getDeptByUser(type) {
    var v = {};
        $.ajax({
            type : 'get',
            url : server_url+'/depts/listdepts?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                //console.log(data);
                $.each(data, function(i, d) {
                    v[d.id] = d.deptname;
                });

                //return JSON.stringify(v);
            }
        });

    return v;
}