/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showDeptSelect(id, type, all) {
    var data = {};
     data = getDept(type);
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
            url : '/depts/listdepts?type=' + type,
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.deptname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}

