/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showWarehouseSelect(id, type, all,decide) {
    var data ;
    if(decide == 0){
         // alert(0);
        data=getWarehouseSbwh(type);
    }

    else if(decide == 1)
        data=getWarehouseBpwh(type);
        // alert(23);
    else if(decide == 9)
        data=getWarehouseBfwh(type);
    // alert(23);
    else
        data=getWarehouse(type);

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

function getWarehouse(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : server_url+'/warehouses/listall',  //?type=' + type,
            async : false,
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getWarehouseSbwh(type) {
    var v = sessionStorage[type];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            // url : '/warehouses/listsbwh?biztype=' + biztype,  //?type=' + type,
            url : server_url+'/warehouses/listsbwh',
            async : false,
            success : function(data) {
                // alert(3);
                console.log(data);
                v = {};
                // alert(4);
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getWarehouseBpwh(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : server_url+'/warehouses/listbpwh',  //?type=' + type,
            async : false,
            success : function(data) {
                console.log(data);
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getWarehouseBfwh(type) {
    var v = sessionStorage[type];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            // url : '/warehouses/listsbwh?biztype=' + biztype,  //?type=' + type,
            url : server_url+'/warehouses/listbfwh',
            async : false,
            success : function(data) {
                // alert(3);
                console.log(data);
                v = {};
                // alert(4);
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });

                sessionStorage[type] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}




function showWarehouseByType(id, type, all,biztype) {
    var data ;
    data=getWarehouseByType(type,biztype);

    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getWarehouseByType(type,biztype) {
    var v = sessionStorage[type + biztype];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : server_url+'/warehouses/listbytype?biztype=' + biztype,
			timeout:10000,//超时时间设置为10秒；
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type + biztype] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type+biztype]);
}



//根据类型和部门提取仓库
function showWarehouseByTypeDept(id, type, all,biztype) {
    var data ;
    data=getWarehouseByTypeDept(type,biztype);

    var select = $("#" + id);
    select.empty();

    if (all != undefined && all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, cname) {
        select.append("<option value ='" + id + "'>" + cname + "</option>");
    });

    return data;
}

function getWarehouseByTypeDept(type,biztype) {
    var v = sessionStorage[type + biztype];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : server_url+'/warehouses/listbytypedept?biztype=' + biztype,
			timeout:10000,//超时时间设置为10秒；
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type + biztype] = JSON.stringify(v);
                //return JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type+biztype]);
}