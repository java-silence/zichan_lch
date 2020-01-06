//值为编码，显示为汉字
function showDictSelect(id, type, all) {
	console.log(id);
	var data = getDict(type);
	var select = $("#" + id);
	select.empty();

	if (all != undefined || all) {

		select.append("<option value=''>全部</option>");
	}

	$.each(data, function(k, v) {
		select.append("<option value ='" + k + "'>" + v + "</option>");
	});

	return data;
}
//显示和取值均为汉字
function showDictNameSelect(id, type, all) {
    // console.log(id);
    var data = getDict(type);
    var select = $("#" + id);
    select.empty();
    // console.log(all);
    if (all != undefined && all) {

        select.append("<option value=''>全部</option>");
    }

    $.each(data, function(k, v) {
        select.append("<option value ='" + v + "'>" + v + "</option>");
    });

    return data;
}

function getDict(type) {
	var v = sessionStorage[type];
	if (v == null || v == "") {
		$.ajax({
			type : 'get',
			url : server_url+'/dicts?type=' + type,
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
			async : false,
			success : function(data) {
				v = {};
				$.each(data, function(i, d) {
					v[d.k] = d.val;
				});

				sessionStorage[type] = JSON.stringify(v);
			}
		});
	}

	return JSON.parse(sessionStorage[type]);
}

function getAllDict(type) {
        var v = [];
        $.ajax({
            type : 'get',
            url : server_url+'/dicts?type=' + type,
            async : false,
            success : function(data) {
                v = data;
            }
        });
        return v;
}