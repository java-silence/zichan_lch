function showInvSelect(id,  all) {
    var data = getInv();
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, d) {
        select.append("<option value ='" + id + "'>" + d + "</option>");
    });

    return data;
}

function getInv() {
    var v = {};

        $.ajax({
            type : 'get',
            url : server_url+'/invs/listall',
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                $.each(data, function(i, d) {
                    v[d.id] = d.invname+"-"+d.invstd;
                });
            }
        });


    return v;
}
