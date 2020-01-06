/**
 * Created by Hezhilin on 2018/2/27 0027.
 */
//
function showCheliangSelect(id, type, all) {
	var data = get(type)
    var select = $("#" + id);
    select.empty();

    if (all != undefined || all) {
        select.append("<option value=''>请选择</option>");
    }

    $.each(data, function(id, clbm) {
        select.append("<option value ='" + id + "'>" + clbm + "</option>");
    });

    return data;
}

function get(type){
	var v = sessionStorage[type];
	 // if (v == null || v == "") {
	    // console.log("ajax");
	    $.ajax({
	        type : 'get',
	        url : server_url+'/cheliangs/listall',
	        async : false,
	        success : function(data) {
	            v = {};
	            $.each(data, function(i, d) {
	                v[d.id] = d.clbm;
	            });
	            sessionStorage[type] = JSON.stringify(v);
	        }
	    });
	 // }
	
	return JSON.parse(sessionStorage[type]);
}