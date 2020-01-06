/**
 * Created by zhoucheng on 2019/11/24.
 */
function showZcCategorySelect(id, type, all) {
    var data = getZcCategory(type);

    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value='0'>请选择资产分类</option>");
    }

    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getZcCategory(type) {
    var v = {};

    $.ajax({
        type : 'get',
        url : '/zcCategorys/listall',  //?type=' + type,
        async : false,
        success : function(data) {
            console.log(data);
            v = {};
            $.each(data, function(i, d) {
                v[d.id] = d.name;
            });
        }
    });

    return v;
}