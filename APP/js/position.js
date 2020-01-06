/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showPositionSelect(id, type, all) {
    var data = getPosition(type);
    console.log(data);
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

function getPosition(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/positions/listall',  //?type=' + type,
            async : false,
            success : function(data) {
                // console.log(13513);
                console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.positionname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}