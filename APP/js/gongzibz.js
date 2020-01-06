/**
 * Created by Hezhilin on 2019/9/19 2148.
 * 工人工资标准
 */
function showGongzibzSelect(id, type, all) {
    var data = {};
     data = getGongzibz(type);
    var select = $("#" + id);
    select.empty();
    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }
    $.each(data, function(id, cname) {
        select.append("<option value ='" + cname + "'>" + cname + "</option>");
    });
    return data;
}

function getGongzibz(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/scGongzibzs/listall?type=' + type,
            async : false,
            success : function(data) {
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

