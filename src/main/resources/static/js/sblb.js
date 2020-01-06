/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showSblbSelect(id, type, all, xtid) {
    var data;

    if(xtid){
        data = getSblbByXtid(xtid);
    }else{
        data = getSblb(type);
    }

    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>请选择设备类别</option>");
    }

    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getSblb(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/sblbs/listall',  //?type=' + type,
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

function getSblbByXtid(xtid) {
    var v = {};
    $.ajax({
        type : 'get',
        url : '/sblbs/listbyxtid?xtid='+xtid,  //?type=' + type,
        async : false,
        success : function(data) {
            console.log(data);
            v = {};
            $.each(data, function(i, d) {
                v[d.id] = d.cname;
            });
        }
    });

    return v;
}