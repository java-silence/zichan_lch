/**
 * Created by fanlinglong on 2019/3/24.
 */
function showcustomerclasssSelect(id, type, all) {
    sessionStorage.clear()
    var data = {};
    if (type == "customerclass"){
        data = getcustomerclass(type);
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

function getcustomerclass(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/customerclasss/listcustomerclass?type=' + type,
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

function getXianlu(type) {
    var v = []
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/customerclasss/listXianlu',
            async : false,
            success : function(data){
                v = data;
            }
        });
    }
    return v;
}