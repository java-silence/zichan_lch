/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
function showEquipmentSelect(id, type, all,decide,lbid,status) {
    var data ;
	
	if(status){
		data = getEqpByStatus(status)
	}else{
			if(lbid){
				data = getEqpByLbid(lbid)
			}else{
				if(decide == 1){
								// alert(0);
								data=getEquipmentAdd(type);
						}
				
						else if(decide == 2)
								data=getEquipmentOldadd(type);
								// alert(23);
						else
								data=getEquipment(type);
			}
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
            url : server_url+'/equipments/listall',  //?type=' + type,
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

function getEqpByLbid(lbid) {
    var v = {};		
        $.ajax({
            type : 'get',
            url : server_url+'/equipments/listbylbid/'+lbid,  //?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                console.log(data);
                $.each(data, function(i, d) {
                    v[d.id] = d.cname;
                });
            }
        });

    return v;
}

function getEqpByStatus(status) {
    var v = {};		
        $.ajax({
            type : 'get',
            url : server_url+'/equipments/listbystatus/'+status,  //?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                console.log(data);
                $.each(data, function(i, d) {
                    v[d.id] = d.cname;
                });
            }
        });
    return v;
}

function getEquipmentAdd(type) {
    var v = sessionStorage[type];
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : server_url+'/equipments/listadd',  //?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
            success : function(data) {
                // alert(3);
                console.log(data);
                v = {};
                // alert(4);
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

function getEquipmentOldadd(type) {
    var v = sessionStorage[type];
    if (v != null || v != "") {
        $.ajax({
            type : 'get',
            url : server_url+'/equipments/listoldadd',  //?type=' + type,
            async : false,
            headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
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
    // alert(1);
    // alert(v);
    if (v != null || v != "") {
        // alert(2);
        $.ajax({
            type : 'get',
            url : server_url+'/equipments/listbywhid/' + whid,
            async : false,
            success : function(data) {
                // alert(3);
                // console.log(data);
                v = {};
                // alert(4);
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
        url : server_url+'/equipments/listbydeptid/'+deptid,  //?type=' + type,
        async : false,
        success : function(data) {
            console.log(data);
            $.each(data, function(i, d) {
                v[d.id] = d.cname;
            });
        }
    });
    return v;
}