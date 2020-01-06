/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showEquipmentSelect(id, type, all,decide,lbid) {
    var data ;
    if(lbid){
        data = getEqpByLbid(lbid)
    }else {
        if (decide == 1) {
            data = getEquipmentAdd(type);
        }
        else if (decide == 2)
            data = getEquipmentOldadd(type);
        else
            data = getEquipment(type);
    }
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

function getEquipment(type) {
    var v = sessionStorage[type];
    if (v == null || v == "") {
        $.ajax({
            type : 'get',
            url : '/equipments/listall',  //?type=' + type,
            async : false,
            success : function(data) {
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

function getEqpByLbid(lbid) {
    var v = {};
    $.ajax({
        type : 'get',
        url : '/equipments/listbylbid/'+lbid,  //?type=' + type,
        async : false,
        success : function(data) {
            $.each(data, function(i, d) {
                v[d.id] = d.cname;
            });
        }
    });
    return v;
}

function getEquipmentAdd(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : '/equipments/listadd',  //?type=' + type,
            async : false,
            success : function(data) {
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

function getEquipmentOldadd(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : '/equipments/listoldadd',  //?type=' + type,
            async : false,
            success : function(data) {
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



function showeqpbywhid(id, type, all,whid) {
    var data ;
    data=geteqpbywhid(type,whid);
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

function geteqpbywhid(type,whid) {
    var v ;
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : '/equipments/listbywhid/' + whid,
            async : false,
            success : function(data) {
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

function showEquipmentbydept(id, type, all,deptid) {
    var data ;
    data = getEqpByDeptid(deptid);
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
function getEqpByDeptid(deptid) {
    var v = {};
    $.ajax({
        type : 'get',
        url : '/equipments/listbydeptid/'+deptid,  //?type=' + type,
        async : false,
        success : function(data) {
            $.each(data, function(i, d) {
                v[d.id] = d.cname;
            });
        }
    });
    return v;
}



//列出登录用户所在部门的所有设备
function showEqplogindept(id, type, all) {
    var data ;
    data = getEqpLoginDept();
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
function getEqpLoginDept() {
    var v = {};
    $.ajax({
        type : 'get',
        url : '/equipments/listlogindept/',  //?type=' + type,
        async : false,
        success : function(data) {
            $.each(data, function(i, d) {
                v[d.id] = d.cname;
            });
        }
    });
    return v;
}