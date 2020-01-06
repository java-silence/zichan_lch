/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showCjSelect(id, type, all) {
    var data = getCj(type);
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>全部</option>");
    }

    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getCj(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/changjias/listall',  //?type=' + type,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data) {
                console.log(data);
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