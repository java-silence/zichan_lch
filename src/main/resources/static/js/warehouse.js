/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showWarehouseSelect(id, type, all,decide) {
    var data ;
    if(decide == 0){
        data=getWarehouseSbwh(type);
    }
    else if(decide == 1)
        data=getWarehouseBpwh(type);
    else if(decide == 9)
        data=getWarehouseBfwh(type);
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
            url : '/warehouses/listall',  //?type=' + type,
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getWarehouseSbwh(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : '/warehouses/listsbwh',
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type] = JSON.stringify(v);
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
            url : '/warehouses/listbpwh',  //?type=' + type,
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function getWarehouseBfwh(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : '/warehouses/listbfwh',
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type] = JSON.stringify(v);
            }
        });
    }

    return JSON.parse(sessionStorage[type]);
}

function showWarehouseByType(id, type, all,biztype,dept) {
    var data ;
    if(dept =="1"){
        data = getWarehouseByTypeDept(type,biztype);
    }else{
        data=getWarehouseByType(type,biztype);
    }
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

//根据类型找仓库
function getWarehouseByType(type,biztype) {
    var v = sessionStorage[type + biztype];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : '/warehouses/listbytype?biztype=' + biztype,
            timeout:10000,//超时时间设置为10秒；
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type + biztype] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type+biztype]);
}

//根据类型和部门找仓库
function getWarehouseByTypeDept(type,biztype) {
    var v = sessionStorage[type + biztype];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : '/warehouses/listbytypedept?biztype=' + biztype,
            timeout:10000,//超时时间设置为10秒；
            async : false,
            success : function(data) {
                v = {};
                $.each(data, function(i, d) {
                    v[d.id] = d.whname;
                });
                sessionStorage[type + biztype] = JSON.stringify(v);
            }
        });
    }
    return JSON.parse(sessionStorage[type+biztype]);
}