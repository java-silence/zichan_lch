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
            url : '/invs/listall',
            async : false,
            success : function(data) {
                $.each(data, function(i, d) {
                    v[d.id] = d.invname+"-"+d.invstd;
                });
            }
        });
    return v;
}

function getinvoptions(invcid) {
    var mydata;
    $.ajax({
        type : 'get',
        url : '/invs/optionsbyinvcid/' + invcid,
        async : false,
        success : function(data) {
            mydata = data;
        }
    });
    return mydata;
}

function getInvById(invid){
    var mydata;
    $.ajax({
        type : 'get',
        url : '/invs/' + invid,
        async : false,
        success : function(data) {
            mydata = data;
        }
    });
    return mydata;
}


function showInvbywhid(id,  whid) {
    var invcid;
    if(whid==10){
        invcid =3;
    } else if(whid == 5){
        invcid =13;
    }else if(whid == 6){
        invcid =15;
    }else {
        invcid =14;
    }

    var data = getinvoptions(invcid);
    var select = $("#" + id);
    select.empty();

    console.log(JSON.stringify(data));
    $.each(data, function(id, d) {

        select.append("<option value ='" + d.id + "'>" + d.name+ "</option>");
    });

    return data;
}

function getinvGroupInvname(invcid) {
    var mydata=[];
    $.ajax({
        type : 'get',
        url : '/invs/getinvGroupInvname/' + invcid,
        async : false,
        success : function(data) {
            mydata = data;
        }
    });
    return mydata;
}

//根据存货类型，提取存货资料到select option
function getinvbyppath(ppath) {
    var mydata;
    $.ajax({
        type : 'get',
        url : '/invs/getinvbyppath/' + ppath,
        async : false,
        success : function(data) {
            mydata = data;
        }
    });
    return mydata;
}

