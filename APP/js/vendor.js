function showVenSelect(id, type, all) {
	// console.log(id);
	var data = getVendor(type);
	var select = $("#" + id);
	select.empty();

	if (all != undefined && all) {
		select.append("<option value=''>请选择</option>");
	}
    // console.log(data);
	$.each(data, function(k, v) {
		select.append("<option value ='" + k + "'>" + v + "</option>");
	});

	return data;
}

function getVendor(type) {
	var v = sessionStorage[type];
	// console.log('v' + v);
	// if (v == null || v == "") {
		$.ajax({
			type : 'get',
			url : server_url+'/vendors/listctype',
			headers:{'token':JSON.parse(localStorage.getItem('$state')).token},
			async : false,
			success : function(data) {
				// console.log(data);
				v = {};
				$.each(data, function(i, d) {
                    v[d.id] = d.cname;
				});

				sessionStorage[type] = JSON.stringify(v);
			}
		});
	// }

	return JSON.parse(sessionStorage[type]);
}
