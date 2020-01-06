/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showpfSelect(id, type, all) {
    var data = getpf(type);
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

function getpf(type) {
    var v = {};

        $.ajax({
            type : 'get',
            url : '/scPeifangs/listall?type=' + type,
            async : false,
            success : function(data) {
                $.each(data, function(i, d) {
                    v[d.id] = d.cname;
                });
            }
        });
    return v;
}