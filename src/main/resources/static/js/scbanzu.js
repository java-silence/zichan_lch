/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showBanzuSelect(id, type, all) {
    var data = getBanzu(type);
    var select = $("#" + id);
    select.empty();
    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }
    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getBanzu(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/scBanzus/listall',  //?type=' + type,
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

