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
        select.append("<option value=''>全部(不选默认为顶级节点)</option>");
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
            url : '/chfls/listchfls?type=' + type,
            async : false,
            success : function(data){
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.cname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type]);
}