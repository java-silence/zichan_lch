/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showJizuSelect(id, type, all) {
    var data = getJizu(type);
    // console.log(data);
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

function getJizu(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/scJizus/listall',  //?type=' + type,
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data) {
                // console.log(data);
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

